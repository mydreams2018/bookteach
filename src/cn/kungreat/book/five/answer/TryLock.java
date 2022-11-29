package cn.kungreat.book.five.answer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
* 多线程并发拿锁要求不阻塞立即返回并输出线程名称
*/
public class TryLock {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable,"A").start();
        new Thread(myRunnable,"B").start();
        new Thread(myRunnable,"C").start();
        new Thread(myRunnable,"D").start();
    }

    private static final class MyRunnable implements Runnable{

        @Override
        public void run() {
            if(LOCK.tryLock()){
                try {
                    System.out.println(Thread.currentThread().getName()+":拿到锁");
                } finally {
                    LOCK.unlock();
                }
            }else{
                System.out.println(Thread.currentThread().getName()+":没拿到锁");
            }
        }
    }
}
