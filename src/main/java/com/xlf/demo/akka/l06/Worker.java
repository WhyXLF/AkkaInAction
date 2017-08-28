package com.xlf.demo.akka.l06;

import akka.actor.AbstractActor;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class Worker extends AbstractActor{


    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
