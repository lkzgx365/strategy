package org.test.celue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.test.celue.entity.OrderDTO;
import org.test.celue.handler.AbstractHandler;
import org.test.celue.handler.HandlerContext;
import org.test.celue.service.IOrderService;

/**
 * @Description:
 * @Author:
 * @Date:
 *
 **/
@Qualifier("orderService")
@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private HandlerContext handlerContext;

    @Override
    public String handle(OrderDTO dto) {
        AbstractHandler instance = handlerContext.getInstance(dto.getType());
        return instance.handle(dto);
    }
}
