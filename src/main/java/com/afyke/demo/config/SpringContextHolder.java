package com.afyke.demo.config;

import com.afyke.demo.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * bean的生命周期，spring一启动就会执行（容器启动）
 */
@Component
public class SpringContextHolder implements BeanNameAware,ApplicationContextAware, BeanFactoryAware, BeanPostProcessor {

    @Autowired
    private OrderService orderService;


    private static String beanName;

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;
    /**
     *  实例化 Bean（new对象）
     */
    public SpringContextHolder() {
        System.out.println("生命周期第一步,构造函数");
    }

    /**
     * 3. Aware 接口：获取当前 Bean 名称
     * 我想知道我的工号（我是谁）
     * BeanNameAware 只能拿到当前这个 Bean 自己的名字。
     */
    @Override
    public void setBeanName(String name) {
        SpringContextHolder.beanName = name;
        System.out.println("3. Aware：BeanNameAware，当前 Bean 名称 = " + name);
    }

    /**
     * 3. Aware 接口：获取 BeanFactory
     * 我想知道人事部在哪（我要找人事部）
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringContextHolder.beanFactory = beanFactory;
        System.out.println("3. Aware：BeanFactoryAware，拿到 BeanFactory");
    }

    /**
     * 3. Aware 接口：获取 ApplicationContext
     * 我想知道整个公司的管理系统在哪（最大权限）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
        System.out.println("3. Aware：ApplicationContextAware，拿到 ApplicationContext");
    }


    /**
     *4.BeanPostProcessor 前置处理 详情见LogProcessor类
     */
/*    @Override
    public Object postProcessBeforeInitialization(
            Object bean,
            String beanName) {

        System.out.println(beanName + " 初始化前");

        return bean;
    }*/


    /** @PostConstruct 通常作用于
     * @Autowried 注入完成后，一些初始化操作
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化中....");
    }





    public static <T>  T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }



}
