package com.afyke.demo.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LogProcessor implements BeanPostProcessor {
    /**
     * 就像公司来了一个保安：
     * 员工A
     * 员工B
     * 员工C
     * 保安
     *
     * 老板说：
     * 以后谁进门
     * 都先经过保安检查
     *
     * 于是：
     * 员工A进门
     * → 保安检查
     *
     * 员工B进门
     * → 保安检查
     *
     * 员工C进门
     * → 保安检查
     *
     * 注意：
     * 员工A不是保安
     * 员工B不是保安
     * 员工C不是保安
     *
     * 但是：
     * 他们都会被保安检查
     *
     *
     * 如果 @PostConstruct 在 LogProcessor 自己里面，它会先执行，因为 Spring 要先把这个“处理器”准备好，后面才能拿它去处理别人。
     * LogProcessor 自己的生命周期处理好了才能让LogProcessor 处理其他 Bean 的生命周期
     * 所以会出现，别人的@PostConstruct在前面
     */

    /**
     * for (Bean bean : 所有Bean) {
     *
     *     创建bean();
     *
     *     logProcessor.before(bean);
     *
     *     初始化bean();
     *
     *     logProcessor.after(bean);
     *
     *     所有bean都会经过这个类，相当于有个保安
     * }
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public Object postProcessBeforeInitialization(
            Object bean,
            String beanName) {

        /**
         * 控制哪些bean打印，哪些bean不打印
         */
        String className = bean.getClass().getName();
        if (className.startsWith("com.afyke.demo")) {
            System.out.println("Bean初始化前：" + beanName);
        }

        /**
         * 把当前 Bean 原样交还给 Spring。
         */
        return bean;
    }
}
