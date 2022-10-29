package cn.kungreat.book.five.one;

import java.util.concurrent.locks.ReentrantLock;

public class OneMain {

    public static void main(String[] args) throws InterruptedException {
        LockRunnable lockRunnable = new LockRunnable();
        Thread threadA = new Thread(lockRunnable, "A");
        Thread threadB = new Thread(lockRunnable, "B");
        threadB.start();
        threadA.start();
        Thread.sleep(200);
        threadB.interrupt();
        threadA.interrupt();
    }

    static class LockRunnable implements Runnable{

        private final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName()+":lock");
                while (true){

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(lock.isHeldByCurrentThread()){
                    System.out.println(Thread.currentThread().getName()+":unlock");
                    lock.unlock();
                }
            }
        }
    }
}
