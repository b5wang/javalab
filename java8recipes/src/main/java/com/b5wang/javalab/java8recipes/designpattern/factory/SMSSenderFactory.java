package com.b5wang.javalab.designpattern.factory;

public class SMSSenderFactory implements SenderFactory {
    @Override
    public Sender produceSender() {
        return new SMSSender();
    }
}
