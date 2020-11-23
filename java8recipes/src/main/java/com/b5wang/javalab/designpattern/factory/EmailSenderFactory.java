package com.b5wang.javalab.designpattern.factory;

class EmailSenderFactory implements SenderFactory {
    @Override
    public Sender produceSender() {
        return new EmailSender();
    }
}
