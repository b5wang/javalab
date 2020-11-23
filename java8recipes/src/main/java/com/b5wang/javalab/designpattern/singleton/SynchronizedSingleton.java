package com.b5wang.javalab.designpattern.singleton;

/**
 * 解决了线程同步的问题，但是每一次调用都要申请锁，效率低下。
 * */
public class SynchronizedSingleton {

    private static SynchronizedSingleton instance = null;

    private SynchronizedSingleton(){}

    public synchronized static SynchronizedSingleton getInstance(){
        if(instance == null){
            instance = new SynchronizedSingleton();
        }

        return instance;
    }

}
