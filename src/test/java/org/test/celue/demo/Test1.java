package org.test.celue.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.test.celue.RunApplication;
import org.test.celue.entity.OrderDTO;
import org.test.celue.service.IOrderService;

import java.util.Arrays;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test1 {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(RunApplication.class);
        ctx.refresh();
        OrderDTO dto = new OrderDTO();
        dto.setCode("1");
        dto.setPrice("1");
        dto.setType("3");
        IOrderService orderService = (IOrderService) ctx.getBean("orderService");
        String handle = orderService.handle(dto);
        System.out.println(handle);
    }
}
