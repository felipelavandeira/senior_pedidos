package com.lavandeira.senior_pedidos.controller.service;

import com.lavandeira.senior_pedidos.exceptionhandler.exception.NotDeletableItemException;
import com.lavandeira.senior_pedidos.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private MessageSource messageSource;


    public void itemIsDeletable(OrderItem orderItem){
        if (!orderItem.getOrders().isEmpty()){
            throw new NotDeletableItemException(
                    messageSource.getMessage("notDeletable.item", null, LocaleContextHolder.getLocale()));
        }
    }
}
