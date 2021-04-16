package com.andrew.akka.hello.world.message;

public class StartMessage {
    public final String startMessage;

    public StartMessage(String startMessage) {
        this.startMessage = startMessage;
    }

    @Override
    public String toString() {
        return startMessage;
    }
}
