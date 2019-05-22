package org.test.celue.bean;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
@Component
public class BeanTool implements ApplicationContextAware {

    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(context == null) {
            BeanTool.context = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return context;  
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }
}
