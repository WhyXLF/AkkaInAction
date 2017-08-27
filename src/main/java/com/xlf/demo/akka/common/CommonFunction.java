package com.xlf.demo.akka.common;

import akka.actor.ActorSystem;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class CommonFunction {
    public static final ActorSystem getContext(){
        return ActorSystem.create("helloAkka");
    }
}
