package com.xlf.demo.akka.l06;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class Echo extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(o -> getSender().tell(o, getSelf()))
                .build();
    }
}
