package com.xlf.demo.akka.l01;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class Printer extends AbstractActor {
    static Props props() {
        return Props.create(Printer.class, Printer::new);
    }

    public static class Greeting {
        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, greeting -> System.out.println(greeting.message))
                .build();
    }
}
