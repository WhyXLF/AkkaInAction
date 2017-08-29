package com.xlf.demo.akka.l06;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class Workers extends AbstractActor{
    @Override
    public void preStart() throws Exception {
        getContext().actorOf(Props.create(Worker.class),"w1");
        getContext().actorOf(Props.create(Worker.class),"w2");
        getContext().actorOf(Props.create(Worker.class),"w3");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
