package com.b5wang.javalab.designpattern.factory;

class FactoryMethod {

    public static Sender produceEmailSender() {
        return new EmailSender();
    }

    public static Sender produceSMSSender() {
        return new SMSSender();
    }

    public static void main(String[] args){
        Sender sender1 = FactoryMethod.produceSMSSender();
        sender1.send();

        Sender sender2 = FactoryMethod.produceEmailSender();
        sender2.send();
    }

}
