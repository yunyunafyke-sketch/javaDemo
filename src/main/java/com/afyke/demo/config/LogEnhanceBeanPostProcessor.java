package com.afyke.demo.config;

import com.afyke.demo.service.OrderService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class LogEnhanceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

        if (bean instanceof OrderService) {
            /**
             * Proxy.newProxyInstance(
             *     类加载器,
             *     要代理的接口,
             *     方法调用处理器
             * )
             */
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(bean, args);
                        }

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