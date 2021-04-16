package com.andrew.akka.hello.world.message;

public class ChangeMessage {
    public final String newMessage;

    public ChangeMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    @Override
    public String toString() {
       return newMessage;
    }
}
