package com.andrew.akka.hello.world.spring;


import org.springframework.stereotype.Component;

@Component
public @interface Actor {

    Class<? extends akka.actor.Actor> value() default akka.actor.AbstractActor.class;
}
