package cn.kungreat.book.five.two;

import java.util.concurrent.locks.ReentrantLock;

public class OthreMethod {

    public static void main(String[] args) throws InterruptedException {
        LockRunnable lockRunnable = new LockRunnable();
        Thread threadA = new Thread(lockRunnable, "A");
        threadA.start();
        Thread.sleep(500);//睡眠500毫秒
        lockRunnable.lock.lock();
        try {
            System.out.println("main:"+lockRunnable.lock.getHoldCount());//获得当前线程对此锁的计数器
        } finally {
            lockRunnable.lock.unlock();
        }
    }
    static class LockRunnable implements Runnable {

        private final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("run:"+lock.getHoldCount());//获得当前线程对此锁的计数器
                addCount();
            } finally {
                lock.unlock();
            }
            System.out.println("end:"+lock.getHoldCount());
        }

        public void addCount(){
            lock.lock();
            try {
                Thread.sleep(2000);
                System.out.println("addCount:"+lock.getHoldCount());//获得当前线程对此锁的计数器
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
}
