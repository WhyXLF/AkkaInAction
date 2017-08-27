package com.xlf.demo.akka.l02;

import akka.actor.*;
import com.xlf.demo.akka.l01.Printer;

/**
 * author: xiaoliufu
 * date:   2017/8/26.
 * description:
 */
public class DependencyInjector implements IndirectActorProducer {
    private final Object applicationContext;
    private final String beanName;

    public DependencyInjector(Object applicationContext, String beanName) {
        this.applicationContext = applicationContext;
        this.beanName = beanName;
    }

    @Override
    public TheActor produce() {
        TheActor result;
        result = new TheActor((String) applicationContext);
        return result;
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return TheActor.class;
    }
}
