package com.andrew.akka.hello.world.spring;

import akka.actor.Actor;
import com.andrew.akka.hello.world.actor.AkkaGoodByeActor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

@Configuration
public class BeanDefinitionRegistryPostProcessorActor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        BeanDefinition definition = new RootBeanDefinition(AkkaGoodByeActor.class);
//        beanDefinitionRegistry.registerBeanDefinition(AkkaGoodByeActor.class.getCanonicalName(), definition);

//        BeanDefinition definition = new RootBeanDefinition(Actor.class);
//        definition.setFactoryBeanName("appConfiguration");
//        definition.setFactoryMethodName("akkaHello1");
//        beanDefinitionRegistry.registerBeanDefinition(AkkaGoodByeActor.class.getCanonicalName(), definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
