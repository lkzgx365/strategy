package org.test.celue.handler.impl;

import org.springframework.stereotype.Component;
import org.test.celue.annotation.HandlerType;
import org.test.celue.entity.OrderDTO;
import org.test.celue.handler.AbstractHandler;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
@Component
@HandlerType("2")
public class TowHandler extends AbstractHandler {
    @Override
    public String handle(OrderDTO dto) {
        return "tow";
    }
}
