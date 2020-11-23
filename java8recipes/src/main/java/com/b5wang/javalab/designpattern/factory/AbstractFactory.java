package com.b5wang.javalab.designpattern.factory;

public class AbstractFactory {

    public static void main(String[] args){
        SenderFactory factory1 = new EmailSenderFactory();
        Sender sender1 = factory1.produceSender();
        sender1.send();

        SenderFactory factory2 = new SMSSenderFactory();
        Sender sender2 = factory2.produceSender();
        sender2.send();
    }

}
