package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.waitnnotify;


class PrintThread implements Runnable {

    private static int threadCount = 0;

    private static int flag = 0;

    private static Object lock = new Object();

    private int indicator;

    private String content;

    public PrintThread(String content){
        this.indicator = threadCount++;
        this.content = content;
        System.out.println("indicator=" + indicator + ", content=" + content + ", flag=" + flag);
    }

    private void increaseFlag(){
        synchronized (lock){
            flag = (flag + 1) % threadCount;
            lock.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            while (flag != indicator) {
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print(content);
            increaseFlag();
        }
    }

    public static void main(String[] args){
        PrintThread pa = new PrintThread("aa");
        PrintThread pb = new PrintThread("bb");
        PrintThread pc = new PrintThread("cc");

        new Thread(pa).start();
        new Thread(pb).start();
        new Thread(pc).start();
    }
}
