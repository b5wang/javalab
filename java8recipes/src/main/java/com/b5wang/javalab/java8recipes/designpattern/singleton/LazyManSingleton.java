package com.b5wang.javalab.designpattern.singleton;

/**
 * 只有使用单例的时候才调用getInstance()，instance才会被加载，节省内存。
 * 有多线程同步问题，创建多个实例；使用者也可能拿到的是不同的实例。
 * */
public class LazyManSingleton {

    private static LazyManSingleton instance = null;

    private LazyManSingleton(){}

    public static LazyManSingleton getInstance(){
        if(instance == null){
            instance = new LazyManSingleton();
        }
        return instance;
    }

}
