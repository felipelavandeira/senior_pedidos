package com.lavandeira.senior_pedidos.controller.service;

import com.lavandeira.senior_pedidos.exceptionhandler.exception.ClosedOrderDiscountException;
import com.lavandeira.senior_pedidos.exceptionhandler.exception.DisabledItemException;
import com.lavandeira.senior_pedidos.model.Order;
import com.lavandeira.senior_pedidos.model.OrderItem;
import com.lavandeira.senior_pedidos.model.Product;
import com.lavandeira.senior_pedidos.model.Service;
import com.lavandeira.senior_pedidos.model.enumerated.OrderStatus;
import com.lavandeira.senior_pedidos.model.repository.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class OrderServiceTest {

    private OrderService service;
    private Order order;
    private BigDecimal expectedTotal;
    private OrderItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        this.service = new OrderService();
        this.expectedTotal = new BigDecimal("150").setScale(5, RoundingMode.HALF_UP);
        createMocks();
        createResults();
    }

    private void createResults() {
        Mockito.when(itemRepository.findAllById(Mockito.anySet()))
                .thenReturn(createOrderItems());
    }

    private void createMocks() {
        MessageSource messageSource = Mockito.mock(MessageSource.class);
        itemRepository = Mockito.mock(OrderItemRepository.class);
        inject(service, "messageSource", messageSource);
        inject(service, "itemRepository", itemRepository);
        createOrder();
    }

    private void createOrder() {
        this.order = new Order();
        order.setDiscount(new BigDecimal("50"));
        order.setStatus(OrderStatus.OPENED);
        order.setItems(createOrderItems());
    }

    private List<OrderItem> createOrderItems() {
        Product product = new Product();
        product.setActive(true);
        product.setName("Produto Teste");
        product.setPrice(new BigDecimal("100"));
        product.setId(1L);

        Service service = new Service();
        service.setActive(true);
        service.setName("Serviço Teste");
        service.setPrice(new BigDecimal("100"));
        service.setId(2L);

        List<OrderItem> items = new ArrayList<OrderItem>();
        items.add(product);
        items.add(service);
        return items;
    }

    private void inject(Object subject, String fieldName, Object value) {
        try {
            Field field = subject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(subject, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void calculateTotal() {
        Order result = service.calculateTotal(order);
        assertEquals(expectedTotal, result.getTotal());
    }

    @Test
    void discountIsApplicable_WithException() {
        this.order.setStatus(OrderStatus.CLOSED);
        assertThrows(ClosedOrderDiscountException.class, () -> {
            service.discountIsApplicable(order);
        });
    }

    @Test
    void discountIsApplicable() {
        assertDoesNotThrow(() -> {
            service.discountIsApplicable(order);
        });
    }

    @Test
    void getItems() {
        assertDoesNotThrow(() -> {
            service.getItems(order);
        });
    }

    @Test
    void getItemsWithDisabled(){
        List<OrderItem> items = createOrderItems();
        items.forEach(item -> {
            item.setActive(false);
        });
        Mockito.when(itemRepository.findAllById(Mockito.anySet()))
                .thenReturn(items);
        assertThrows(DisabledItemException.class, () -> {
            service.getItems(order);
        });
    }
}