package com.xlf.demo.akka.l02;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class TheActor extends AbstractActor {


    public String message;

    public TheActor(String message) {
        this.message = message;
    }

    @Override
    public Receive createReceive() {
        return new ReceiveBuilder()
                .match(String.class, msg -> {
                    getSender().tell(message,getSelf());
                })
                .build();

    }
}
