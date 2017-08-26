package com.xlf.demo.akka.l01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class Main {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("helloAkka");
        try {
            final ActorRef printerRef = system.actorOf(Printer.props(), "printerActor");
            final ActorRef helloGreeterRef = system.actorOf(Greeter.props("hello", printerRef));
            final ActorRef welcomeGreeterRef = system.actorOf(Greeter.props("welcome", printerRef));

            helloGreeterRef.tell(new Greeter.WhoToGreet("xiaoliufu"), ActorRef.noSender());
            helloGreeterRef.tell(new Greeter.Greet(), ActorRef.noSender());

            welcomeGreeterRef.tell(new Greeter.WhoToGreet("chenniping"), ActorRef.noSender());
            welcomeGreeterRef.tell(new Greeter.Greet(), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            system.terminate();
        }

    }
}
