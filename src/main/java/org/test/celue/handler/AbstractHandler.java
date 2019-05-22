package org.test.celue.handler;

import org.test.celue.entity.OrderDTO;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public abstract class AbstractHandler {

    abstract public String handle(OrderDTO dto);
}
