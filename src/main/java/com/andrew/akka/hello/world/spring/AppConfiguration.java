package com.andrew.akka.hello.world.spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.andrew.akka.hello.world"})
public class AppConfiguration {

    @Autowired
    private  ApplicationContext applicationContext;

    private final ActorSystem system;

    public AppConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        system = ActorSystem.create("akka_system");
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
    }

    @Bean
    public ActorSystem actorSystem() {
        return system;
    }

    @Bean
    public ActorRef akkaHelloWorldActor() {
        return system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("akkaHelloWorldActor"), "akkaHelloWorldActor");
    }

    @Bean
    public ActorRef akkaGoodByeActor() {
        return system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("akkaGoodByeActor"), "akkaGoodByeActor");
    }

}
