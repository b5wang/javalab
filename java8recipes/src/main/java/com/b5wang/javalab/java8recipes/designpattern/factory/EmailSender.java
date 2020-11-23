package com.b5wang.javalab.designpattern.factory;

class EmailSender implements Sender {
    @Override
    public void send() {
        System.out.println("Sent an Email...");
    }
}
