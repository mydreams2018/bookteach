package cn.kungreat.book.five.answer;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
   static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
   static boolean cacheValid = false;

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
            rwl.readLock().lock();
            if (!cacheValid) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (!cacheValid) {
                        System.out.println("模拟数据写操作....");
                        cacheValid = true;
                    }
                    //通过在释放写锁之前拿到读锁来完成锁降级
                    rwl.readLock().lock();
                } finally {
                    rwl.writeLock().unlock();//释放写锁
                }
            }
            try {
                System.out.println("模拟数据读操作....");
            } finally {
                rwl.readLock().unlock();
            }
        }
    }
}
