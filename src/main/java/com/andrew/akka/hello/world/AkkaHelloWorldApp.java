package com.andrew.akka.hello.world;

import com.andrew.akka.hello.world.actor.Actors;
import com.andrew.akka.hello.world.spring.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class AkkaHelloWorldApp {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Actors actors = (Actors) context.getBean("actors");
        actors.start();
    }
}
