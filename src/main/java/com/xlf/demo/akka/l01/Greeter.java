package com.xlf.demo.akka.l01;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class Greeter extends AbstractActor {
    static Props props(String message, ActorRef printerRef) {
        return Props.create(Greeter.class, () -> new Greeter(message, printerRef));
    }

    static public class WhoToGreet {
        public final String who;

        public WhoToGreet(String who) {
            this.who = who;
        }
    }

    static public class Greet {
    }

    private final String message;
    private final ActorRef printerActor;
    private String greeting = "";

    public Greeter(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(WhoToGreet.class, wtg -> this.greeting = message + "," + wtg.who)
                .match(Greet.class, greet -> printerActor.tell(new Printer.Greeting(greeting), getSelf()))
                .build();
    }
}
