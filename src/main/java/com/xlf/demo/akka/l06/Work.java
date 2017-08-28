package com.xlf.demo.akka.l06;

import java.io.Serializable;

/**
 * author: xiaoliufu
 * date:   2017/8/28.
 * description:
 */
public class Work implements Serializable{
    private static final long serialVersionUID = 3811782339429021965L;
    public final String payload;

    public Work(String payload) {
        this.payload = payload;
    }
}
