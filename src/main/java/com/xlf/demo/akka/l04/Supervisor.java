package com.xlf.demo.akka.l04;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * author: xiaoliufu
 * date:   2017/8/27.
 * description:
 */
public class Supervisor extends AbstractActor {
    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES), t -> {
        if (t instanceof ArithmeticException) {
            System.out.println("meet ArithmeticException,just resume");
            return SupervisorStrategy.resume();
        } else if (t instanceof NullPointerException) {
            System.out.println("meet NullPointerException,restart");
            return SupervisorStrategy.restart();
        } else {
            return SupervisorStrategy.escalate();
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(Props.class,props -> {
            getContext().actorOf(props,"restartActor");
        }).matchAny(this::unhandled).build();
    }
}
