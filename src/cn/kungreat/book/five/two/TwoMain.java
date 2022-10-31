package cn.kungreat.book.five.two;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TwoMain {

    public static void main(String[] args) {
        LockRunnable lockRunnable = new LockRunnable();
        Thread threadA = new Thread(lockRunnable, "A");
        Thread threadB = new Thread(lockRunnable, "B");
        threadA.start();
        threadB.start();
    }

    static class LockRunnable implements Runnable {

        private final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                //最大等待时间充足,二个线程都可以拿到锁
                if (lock.tryLock(5,TimeUnit.SECONDS)) {//尝试拿锁并最大等待给定的时间段
                    System.out.println(Thread.currentThread().getName() + ":getLock");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();//释放锁
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + ":noLock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
