package com.b5wang.javalab.designpattern.singleton;

/**
 * 解决线程同步和同步方法的效率问题。
 * volatile关键字保证了初始化instance是一个原子操作
 *
 * */
public class DoubleCheckLockSingleton {

    private static volatile DoubleCheckLockSingleton instance = null;

    private DoubleCheckLockSingleton(){}

    public static DoubleCheckLockSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckLockSingleton.class){
                if(instance == null){
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }

}
