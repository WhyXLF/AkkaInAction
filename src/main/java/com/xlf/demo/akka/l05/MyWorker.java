package com.xlf.demo.akka.l05;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

/**
 * author: xiaoliufu
 * date:   2017/8/27.
 * description:
 */
public class MyWorker extends AbstractActor {
    public static enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create()
                .matchEquals(Msg.WORKING, msg -> {
                    System.out.println("I am working");
                })
                .matchEquals(Msg.DONE, msg -> {
                    System.out.println("Stop working");
                })
                .matchEquals(Msg.CLOSE, msg -> {
                    System.out.println("I will shutdown");
                    getSender().tell(Msg.CLOSE, getSelf());
                    getContext().stop(getSelf());
                })
                .matchAny(this::unhandled)
                .build();
    }
}
