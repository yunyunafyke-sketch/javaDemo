package com.afyke.demo.config;

import com.afyke.demo.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class LogEnhanceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

        if (bean instanceof UserService) {

            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("方法执行前：" + method.getName());

                        Object result = method.invoke(bean, args);

                        System.out.println("方法执行后：" + method.getName());

                        return result;
                    }
            );
        }

        return bean;
    }
}