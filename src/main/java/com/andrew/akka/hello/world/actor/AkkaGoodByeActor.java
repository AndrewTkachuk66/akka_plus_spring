package com.andrew.akka.hello.world.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.andrew.akka.hello.world.message.ChangeMessage;
import com.andrew.akka.hello.world.spring.Actor;
import org.springframework.beans.factory.annotation.Autowired;

@Actor
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AkkaGoodByeActor extends AbstractActor {

    @Autowired @Actor(AkkaHelloActor.class)
    private ActorRef akkaHello;

    @Override
    public Receive createReceive() {
       return receiveBuilder()
                .match(ChangeMessage.class, this::onSayBye)
                .build();
    }

    private void onSayBye(ChangeMessage message) {
        akkaHello.tell(new ChangeMessage("GoodBye AkkaHelloWorldActor"), getSelf());
    }

}
