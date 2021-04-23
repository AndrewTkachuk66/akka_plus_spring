package com.andrew.akka.hello.world.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import com.andrew.akka.hello.world.message.ChangeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AkkaGoodByeActor extends AbstractActor {

    @Autowired
    @Qualifier("akkaHello")
    private ActorRef AkkaHelloWorldActor;


    @Override
    public Receive createReceive() {
       return receiveBuilder()
                .match(ChangeMessage.class, this::onSayBye)
                .build();
    }

    private void onSayBye(ChangeMessage message) {
        AkkaHelloWorldActor.tell(new ChangeMessage("GoodBye AkkaHelloWorldActor"), getSelf());
    }

}
