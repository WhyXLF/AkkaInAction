package com.xlf.demo.akka.l07;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.xlf.demo.akka.l05.MyWorker;

import java.util.ArrayList;
import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/8/29.
 * description:
 */
public class WatchActor extends AbstractActor {
    private Router router;

    {
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef worker = getContext().actorOf(Props.create(MyWorker.class), "worker_" + i);
            getContext().watch(worker);
            routees.add(new ActorRefRoutee(worker));
        }
        router = new Router(new RoundRobinRoutingLogic(),routees);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MyWorker.Msg.class, msg -> router.route(msg, getSender()))
                .match(Terminated.class, terminated -> {
                    router = router.removeRoutee(terminated.actor());
                    System.out.println(terminated.actor().path() + " is closed,routees=" + router.routees());
                }).matchAny(this::unhandled)
                .build();
    }
}
