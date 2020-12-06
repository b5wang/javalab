package com.b5wang.javalab.java8recipes.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TryLock {

    private Lock lock = new ReentrantLock();

    void process(){
        /**
         * 标准的tryLock流程
         * */
        if(lock.tryLock()) {
            try{
                //处理任务
                System.out.println(Thread.currentThread().getName() + " start processing");
                Thread.sleep(1000*2);
                System.out.println(Thread.currentThread().getName() + " end processing!!!");
            }catch(Exception ex){
                ex.printStackTrace();
            }finally{
                //当获取锁成功时最后一定要记住finally去关闭锁
                lock.unlock();   //释放锁
            }
        }else {
            //else时为未获取锁，则无需去关闭锁
            //如果不能获取锁，则直接做其他事情
            System.out.println(Thread.currentThread().getName() + " can't start due to fail to get lock!!!");
        }
    }


    public static void main(String[] args){
        TryLock tl = new TryLock();

        Thread t1 = new Thread(tl::process);
        t1.setName("AAA");
        Thread t2 = new Thread(tl::process);
        t2.setName("BBB");

        t1.start();
        t2.start();
    }

}
