package com.xlf.demo.akka.l04;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import scala.Option;

/**
 * author: xiaoliufu
 * date:   2017/8/27.
 * description:
 */
public class RestartActor extends AbstractActor {
    public enum Msg {
        DONE, RESTART
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("preStart hashcode:" + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("postStop hashcode:" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart hashcode:" + this.hashCode());
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("preRestart hashcode:" + this.hashCode());
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .matchEquals(Msg.DONE, msg -> {
                    getContext().stop(getSelf());
                })
                .matchEquals(Msg.RESTART, msg -> {
                    System.out.println(((Object) null).toString());
                    double a = 0 / 0;
                }).matchAny(o -> {
                    unhandled(o);
                }).build();
    }
}
