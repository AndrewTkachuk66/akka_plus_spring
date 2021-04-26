package com.andrew.akka.hello.world.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.andrew.akka.hello.world.message.ChangeMessage;
import com.andrew.akka.hello.world.message.StartMessage;
import com.andrew.akka.hello.world.spring.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Actor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AkkaHelloActor extends AbstractActor {

    @Autowired @Actor()
    private ActorRef akkaGoodBye;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StartMessage.class, this::onSayHello)
                .match(ChangeMessage.class, this::onChangeMessage)
                .build();
    }

    private void onChangeMessage(ChangeMessage message) {
        System.out.println(message.toString());
    }

    private void onSayHello(StartMessage message) {
        System.out.println(message.toString());
    }

}
