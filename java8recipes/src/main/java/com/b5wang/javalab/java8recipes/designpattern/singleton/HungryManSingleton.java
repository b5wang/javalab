package com.b5wang.javalab.designpattern.singleton;

/**
 * 最简单的单例模式实现方式。
 * 1. 任何单例模式，构造函数必须是private
 * 2. 在类加载的时候创建一次实例，不会存在多个线程创建多个实例的情况，避免了多线程同步的问题。
 * 3. 如果没有调用getInstance()，只是类加载而已，实例被创建但没有使用，会浪费内存。
 *
 * */
public class HungryManSingleton {

    private final static HungryManSingleton instance = new HungryManSingleton();

    private HungryManSingleton(){
        System.out.println("Init HungryManModeSingleton");
    }

    public static HungryManSingleton getInstance(){
        return instance;
    }

    public static void main(String[] args){
        System.out.println("HungryManModeSingleton");
    }

}
