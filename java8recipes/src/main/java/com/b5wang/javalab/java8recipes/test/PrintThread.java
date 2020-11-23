package com.b5wang.javalab.test;

public class PrintThread implements Runnable {

    private static int counter = 0;

    private int flag;

    private String str;

    private PrintThread t;

    public PrintThread(int flag,String str){
        this.flag = flag;
        this.str = str;
    }

    public void setTread(PrintThread t){
        this.t = t;
    }

    private synchronized void changeCounter(){
        synchronized (PrintThread.class){
            counter = (counter == 0) ? 1 : 0;
            synchronized (t) {
                t.notify();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            while (counter != flag) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print(str);
            changeCounter();
        }
    }

    public static void main(String[] args){
        PrintThread pa = new PrintThread(0,"aa");
        PrintThread pb = new PrintThread(1,"bb");
        pa.setTread(pb);
        pb.setTread(pa);


        new Thread(pa).start();
        new Thread(pb).start();
    }
}
