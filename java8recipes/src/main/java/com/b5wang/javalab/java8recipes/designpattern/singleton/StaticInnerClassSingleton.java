package com.b5wang.javalab.designpattern.singleton;

/**
 * 类似lazy man，只有调用getInstance()时候才会初始化，同时又避免了线程同步的问题。
 * */
public class StaticInnerClassSingleton {

    private static class SingletonInstanceHolder{
        public static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton(){
        System.out.println("StaticInnerClassSingleton init");
    }

    public static StaticInnerClassSingleton getInstance(){
        return SingletonInstanceHolder.instance;
    }

    public static void main(String[] args){
        System.out.println("StaticInnerClassSingleton");
    }

}
