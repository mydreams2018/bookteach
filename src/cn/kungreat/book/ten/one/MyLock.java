package cn.kungreat.book.ten.one;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/*
* 官方文档示例 非重入互斥锁
*/
public class MyLock {
    private final Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {
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

    public void lock()              { sync.acquire(1); }
    public boolean tryLock()        { return sync.tryAcquire(1); }
    public void unlock()            { sync.release(1); }
    public boolean isLocked()       { return sync.isLocked(); }
    public boolean isHeldByCurrentThread() {
        return sync.isHeldExclusively();
    }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

}
