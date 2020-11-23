package com.b5wang.javalab.designpattern.factory;

class SMSSender implements Sender {
    @Override
    public void send() {
        System.out.println("Sent a SMS...");
    }
}
