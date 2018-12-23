package com.yang.resteasy.spring;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class YangWsSpringBeanProcessor extends SpringBeanProcessor {

    public YangWsSpringBeanProcessor(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public YangWsSpringBeanProcessor(ResteasyDeployment deployment) {
        super(deployment);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);
        System.out.println("Yang WS restful endpoints are exposed now");
    }
}
