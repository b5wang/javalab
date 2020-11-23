package com.b5wang.javalab.designpattern.factory;

class SimpleFactory {
    /**
     * 如果senderType是不支持的怎么办？
     * */
    static Sender produceSender(String senderType) {
        if ("SMS".equalsIgnoreCase(senderType)) {
            return new SMSSender();
        } else if ("email".equalsIgnoreCase(senderType)) {
            return new EmailSender();
        } else {
            System.out.println("invaild senderType....");
            return null;
        }
    }

    public static void main(String[] args){
        Sender sender1 = SimpleFactory.produceSender("SMS");
        sender1.send();

        Sender sender2 = SimpleFactory.produceSender("Email");
        sender2.send();
    }
}
