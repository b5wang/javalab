package com.b5wang.javalab.java8recipes.concurrency.volatileex;

class VolatileTest {

    int a = 1;
    int b = 2;

    void change(){
        a = 3;
        b = a;
    }

    void print(){
        System.out.println("b="+b+";a="+a);
    }

    public static void main(String[] args){
        for(int i = 0; i < 1000; i++) {
            final VolatileTest vt = new VolatileTest();

            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    vt.change();
                }
            })).start();

            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    vt.print();
                }
            })).start();
        }
    }

}
