package cn.kungreat.book.five.two;

import java.util.concurrent.locks.ReentrantLock;

public class SpinLock {
    public static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockSpin reentrantLockSpin = new ReentrantLockSpin();
        new Thread(reentrantLockSpin).start();
        new Thread(reentrantLockSpin).start();
        new Thread(reentrantLockSpin).start();
        new Thread(reentrantLockSpin).start();
        new Thread(reentrantLockSpin).start();
    }

    static final class ReentrantLockSpin implements Runnable {

        @Override
        public void run() {
            //循环一定的次数拿锁,可以理解为自旋次数
            for (int i = 0; i < 100; i++) {
                if(REENTRANT_LOCK.tryLock()){//尝试拿锁
                    try {
                        System.out.println("拿到锁后做的操作");
                        return;//已经拿到锁做完任务退出
                    } finally {
                        REENTRANT_LOCK.unlock();
                    }
                }
            }
        }
    }
}
