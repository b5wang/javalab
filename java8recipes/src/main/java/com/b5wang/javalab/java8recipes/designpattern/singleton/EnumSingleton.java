package com.b5wang.javalab.designpattern.singleton;

public enum EnumSingleton {

    INSTANCE;

    private EnumSingleton(){
        System.out.println("EnumSingleton init");
    }

    public static void main(String[] args){
        System.out.println("EnumSingleton");
    }

}
