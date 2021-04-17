package com.lavandeira.senior_pedidos.controller.service;

import com.lavandeira.senior_pedidos.exceptionhandler.exception.ClosedOrderDiscountException;
import com.lavandeira.senior_pedidos.exceptionhandler.exception.DisabledItemException;
import com.lavandeira.senior_pedidos.model.Order;
import com.lavandeira.senior_pedidos.model.OrderItem;
import com.lavandeira.senior_pedidos.model.Product;
import com.lavandeira.senior_pedidos.model.enumerated.OrderStatus;
import com.lavandeira.senior_pedidos.model.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OrderItemRepository itemRepository;

    public Order calculateTotal(Order order){
        AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);
        order.getItems().forEach(item -> {
            if (discountIsApplicable(item)){
                total.set(calculateTotal(item, order.getDiscount(), total.get()));
            }else{
                total.set(total.get().add(item.getPrice()));
            }
        });
        order.setTotal(total.get());
        return order;
    }

    public void discountIsApplicable(Order order) {
        if (!order.getDiscount().equals(BigDecimal.ZERO) && order.getStatus().equals(OrderStatus.CLOSED)){
            throw new ClosedOrderDiscountException(
                    messageSource.getMessage("closedOrder.discount", null, LocaleContextHolder.getLocale()));
        }
    }

    public List<OrderItem> getItems(Order order){
        Set<Long> itemIds = new HashSet<>();
        order.getItems().forEach(item -> {
            itemIds.add(item.getId());
        });
        List<OrderItem> items = itemRepository.findAllById(itemIds);
        validateItems(items);
        return items;
    }

    private BigDecimal calculateTotal(OrderItem item, BigDecimal discount, BigDecimal total) {
        BigDecimal pricewithDiscount = calculatePriceWithDiscount(item.getPrice(), discount);
        return total.add(pricewithDiscount);
    }

    private void validateItems(List<OrderItem> items) {
        items.forEach(item ->{
            if (!item.getActive()){
                throw new DisabledItemException(
                        messageSource.getMessage("createOrder.disabledItem", null, LocaleContextHolder.getLocale()));
            }
        });
    }

    private BigDecimal calculatePriceWithDiscount(BigDecimal price, BigDecimal discount) {
        BigDecimal percentDiscount = discount.divide(BigDecimal.valueOf(100L), 5,RoundingMode.HALF_UP);
        return price.subtract(price.multiply(percentDiscount));
    }

    private boolean discountIsApplicable(OrderItem item) {
        return item instanceof Product;
    }
}
