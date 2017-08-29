package com.xlf.demo.akka.l06;

import akka.actor.AbstractActor;

import java.util.Arrays;
import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class Parent extends AbstractActor{
    //#paths
    List<String> paths = Arrays.asList("/user/workers/w1", "/user/workers/w2",
            "/user/workers/w3");
    @Override
    public Receive createReceive() {
        return ;
    }
}
