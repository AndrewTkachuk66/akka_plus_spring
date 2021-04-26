package com.andrew.akka.hello.world.spring;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.concurrent.atomic.AtomicLong;


@Configuration
public class AutowireCapableBeanFactoryAkka extends AutowiredAnnotationBeanPostProcessor implements ApplicationContextAware {

    private static volatile AtomicLong count = new AtomicLong();
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ActorRef.class.isAssignableFrom(beanClass)) {
            return getActorRef(getActorBeanName(beanName));
        }
        if (Actor.class.isAssignableFrom(beanClass)) {
            return getActorInstance(beanName);
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    private ActorRef getActorRef(String beanName) {
        return actorSystem().actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem())
                .props(beanName), getActorName(beanName, count));
    }

    private Object getActorInstance(String beanName) {
        return null;
    }

    private String getActorBeanName(String beanName) {
        return beanName + "Actor";
    }

    private String getActorName(String actorBeanName, AtomicLong count) {
        return actorBeanName + "_" + count.getAndIncrement();
    }

    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create("akka_system");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
