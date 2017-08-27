package com.xlf.demo.akka.l02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeoutException;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class DependencyInjectorTest {
    private static ActorSystem system = null;

    @BeforeClass
    public static void beforeClass() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void afterClass() throws TimeoutException, InterruptedException {
        Await.ready(system.terminate(), Duration.create("5 seconds"));
    }

    private ActorSystem getContext() {
        return system;
    }

    @Test
    public void indirectActorOf() {
        final String applicationContext = "...";

        final ActorRef myActor = getContext().actorOf(Props.create(DependencyInjector.class, applicationContext, "TheActor"), "TheActor");
    }
}