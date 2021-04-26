package com.andrew.akka.hello.world.spring;

import akka.actor.RepointableActorRef;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Configuration
public class ActorBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Arrays.stream(beanDefinitionRegistry.getBeanDefinitionNames())
                .map(beanDefinitionRegistry::getBeanDefinition)
                .filter(this::isActor)
                .forEach(beanDefinition -> {
                    beanDefinition.setLazyInit(true);

                    BeanDefinition definition = new RootBeanDefinition(RepointableActorRef.class);
                    definition.setFactoryBeanName(getFactoryBeanName());
                    definition.setScope(beanDefinition.getScope());
                    beanDefinitionRegistry.registerBeanDefinition(getActorBeanName(beanDefinition), definition);
                });
    }

    private boolean isActor(BeanDefinition beanDefinition) {
        if (beanDefinition instanceof AnnotatedBeanDefinition)
            return ((AnnotatedBeanDefinition) beanDefinition).getMetadata().hasAnnotation(Actor.class.getCanonicalName());
        return false;
    }

    private String getFactoryBeanName() {
        return StringUtils.uncapitalize(AutowireCapableBeanFactoryAkka.class.getSimpleName());
    }

    private String getActorBeanName(BeanDefinition beanDefinition) {
        String[] splitName = beanDefinition.getBeanClassName().split("\\.");
        return StringUtils.uncapitalize(splitName[splitName.length-1]).replace("Actor", "");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
