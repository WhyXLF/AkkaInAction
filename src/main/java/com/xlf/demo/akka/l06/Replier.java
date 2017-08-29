package com.xlf.demo.akka.l06;

import akka.actor.AbstractActor;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class Replier extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(message -> {
                    getSender().tell("reply", getSelf());

                    getSender().tell("reply", getContext().getParent());
                })
                .build();
    }
}
