package com.andrew.akka.hello.world.spring;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.andrew.akka.hello.world"})
public class AppConfiguration {

    @Autowired
    private  ApplicationContext applicationContext;

    @Autowired
    private final ActorSystem system;

    public AppConfiguration(ApplicationContext applicationContext, ActorSystem system) {
        this.applicationContext = applicationContext;
        this.system = system;
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(this.system).initialize(applicationContext);
    }

//    @Bean
//    public ActorRef akkaHello() {
//        return system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("akkaHelloWorldActor"), "akkaHelloWorldActor");
//    }

//    @Bean
//    public ActorRef akkaGoodBye() {
//        return system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("akkaGoodByeActor"), "akkaGoodByeActor");
//    }
}
