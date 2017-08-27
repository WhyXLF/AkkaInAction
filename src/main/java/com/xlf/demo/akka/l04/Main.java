package com.xlf.demo.akka.l04;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * author: xiaoliufu
 * date:   2017/8/27.
 * description:
 */
public class Main {
    private static void customStrategy(ActorSystem system){
        ActorRef  a = system.actorOf(Props.create(Supervisor.class),"Supervisor");
        a.tell(Props.create(RestartActor.class),ActorRef.noSender());
        ActorSelection selection = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");
        for (int i=0;i<100;i++){
            selection.tell(RestartActor.Msg.RESTART,ActorRef.noSender());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("lifecycle");
        customStrategy(system);
        Thread.sleep(1000000);
    }
}
