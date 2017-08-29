package com.xlf.demo.akka.l07;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.xlf.demo.akka.l05.MyWorker;

/**
 * author: xiaoliufu
 * date:   2017/8/29.
 * description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("route");
        ActorRef w = system.actorOf(Props.create(WatchActor.class), "watcher");
        int i = 1;
        while (true) {
            w.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
            if (i % 10 == 0) w.tell(MyWorker.Msg.CLOSE, ActorRef.noSender());
            i++;
            Thread.sleep(100);
        }
    }
}
