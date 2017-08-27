package com.xlf.demo.akka.l05;

import akka.actor.*;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * author: xiaoliufu
 * date:   2017/8/27.
 * description:
 */
public class Main {

    public static void main(String[] args) throws TimeoutException {
        ActorSystem system = ActorSystem.create("inboxDemo");
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");

        final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);
        inbox.send(worker, MyWorker.Msg.WORKING);
        inbox.send(worker, MyWorker.Msg.DONE);
        inbox.send(worker, MyWorker.Msg.CLOSE);

        while (true) {
            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (msg == MyWorker.Msg.CLOSE) {
                System.out.println("My worker is Closing");
            } else if (msg instanceof Terminated) {
                System.out.println("My worker is closed");
                system.terminate();
                break;
            } else {
                System.out.println(msg);
            }
        }
    }
}
