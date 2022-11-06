package cn.kungreat.book.five.two;

import java.util.concurrent.locks.ReentrantLock;

public class MethodAll {

    private static final ReentrantLock LOCK = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        LockRunnable lockRunnable = new LockRunnable();
        Thread threadA = new Thread(lockRunnable, "A");
        threadA.start();
        Thread.sleep(200);//睡眠200毫秒
        LOCK.lock();//拿锁
        try {
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();//释放锁
        }
    }

    static class LockRunnable implements Runnable {

        @Override
        public void run() {
            LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("getQueueLength:"+LOCK.getQueueLength());
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                Thread[] threads = new Thread[threadGroup.activeCount()];
                threadGroup.enumerate(threads);
                for (int i = 0; i < threads.length; i++) {
                    if(threads[i].getName().equals("main")){
                        System.out.println("hasQueuedThread:"+LOCK.hasQueuedThread(threads[i]));
                    }
                }
                System.out.println("hasQueuedThreads:"+LOCK.hasQueuedThreads());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }

    }
}
