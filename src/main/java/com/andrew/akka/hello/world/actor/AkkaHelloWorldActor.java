package com.andrew.akka.hello.world.actor;


import akka.actor.AbstractActor;
import com.andrew.akka.hello.world.message.ChangeMessage;
import com.andrew.akka.hello.world.message.StartMessage;
import org.springframework.stereotype.Component;

@Component
public class AkkaHelloWorldActor extends AbstractActor {

    private AkkaHelloWorldActor() {
    }

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
