package com.andrew.akka.hello.world.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.andrew.akka.hello.world.message.ChangeMessage;
import com.andrew.akka.hello.world.message.StartMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Actors {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    ActorRef akkaHelloWorldActor;

    @Autowired
    ActorRef akkaGoodByeActor;

    public void start() {
        akkaHelloWorldActor.tell(new StartMessage("Hello Akka. I'm AkkaHelloWorldActor"), ActorRef.noSender());
        akkaGoodByeActor.tell(new ChangeMessage("Good bye"), ActorRef.noSender());
        actorSystem.terminate();
    }
}
