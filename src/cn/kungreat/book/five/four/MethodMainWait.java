package cn.kungreat.book.five.four;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MethodMainWait {
    static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = REENTRANT_READ_WRITE_LOCK.writeLock();
    static final Condition condition = WRITE_LOCK.newCondition();
    public static void main(String[] args) throws InterruptedException {
        RunnableMy runnableMy = new RunnableMy();
        new Thread(runnableMy,"A").start();
        new Thread(runnableMy,"B").start();
        Thread.sleep(1000);
        WRITE_LOCK.lock();
        try {
            System.out.println(REENTRANT_READ_WRITE_LOCK.getWaitQueueLength(condition));
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    static class RunnableMy implements Runnable{
        @Override
        public void run() {
            WRITE_LOCK.lock();
            try {
                condition.await();
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                WRITE_LOCK.unlock();
            }
        }

    }
}
