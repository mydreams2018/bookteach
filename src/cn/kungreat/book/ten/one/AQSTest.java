package cn.kungreat.book.ten.one;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AQSTest {
    private static final Sync SYNC = new Sync();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    SYNC.acquireInterruptibly(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(SYNC.isHeldExclusively()){
                        SYNC.release(1);
                    }
                }
            }
        }, "A");
        thread.start();

        SYNC.acquire(1);
        try {
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println(Thread.currentThread().getName());
        }finally {
            SYNC.release(1);
        }
        System.out.println("main-end");
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync() {
//            setState(-1);
        }

        //加锁时回调此方法
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        //释放锁时回调此方法
        protected boolean tryRelease(int releases) {
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        //是否是当前执行线程对象拥有此锁
        public boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        //是否有执行线程对象拥有此锁
        public boolean isLocked() {
            return getState() != 0;
        }
    }
}
