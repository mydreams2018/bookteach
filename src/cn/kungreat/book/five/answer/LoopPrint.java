package cn.kungreat.book.five.answer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 *根据业务要求补全代码,A、B两个线程轮流打印数字,从1~100
 */
public class LoopPrint {
    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();
    private static final Condition CONDITION = REENTRANT_LOCK.newCondition();

    public static void main(String[] args) throws InterruptedException {
        LoopRunnable loopRunnable = new LoopRunnable();
        new Thread(loopRunnable, "A").start();
        new Thread(loopRunnable, "B").start();
    }

    static class LoopRunnable implements Runnable {
        private volatile int num = 1;

        @Override
        public void run() {
            REENTRANT_LOCK.lock();
            try {
                for (; num <= 100; ) {
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;
                    CONDITION.signal();
                    CONDITION.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                CONDITION.signal();
                REENTRANT_LOCK.unlock();
            }
        }
    }
}
