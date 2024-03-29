package com.taogen.example.mybatisplus.crud.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public final class SpringUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }

    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }


    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }

    public static Class<?> getBeanClassType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

    public static String[] getBeanAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }
}
