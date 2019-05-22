package org.test.celue.handler;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.test.celue.annotation.HandlerType;
import org.test.celue.bean.ClassScaner;

import java.util.HashMap;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {
    private static final String HANDLER_PACKAGE = "org.test.celue.handler";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        HashMap<String, Class> handlerMap = Maps.newHashMapWithExpectedSize(3);

        ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class).forEach(clzaa -> {
            String type = clzaa.getAnnotation(HandlerType.class).value();
            handlerMap.put(type, clzaa);
        });

        HandlerContext context = new HandlerContext(handlerMap);
        configurableListableBeanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }
}
