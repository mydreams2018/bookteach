package cn.kungreat.book.ten.one;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/*
* 共享模式 仿 CountDownLatch
**/
public class AQSShareTest {

    private static final Sync sync = new Sync(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sync.releaseShared(1);
                    System.out.println(Thread.currentThread().getName()+":end");
                }
            }).start();
        }
        sync.acquireShared(1);
        System.out.println("main-end");
    }

    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            setState(count);
        }

        int getCount() {
            return getState();
        }

        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }

        protected boolean tryReleaseShared(int releases) {
            for (;;) {
                int c = getState();
                if (c == 0)
                    return false;
                int nextc = c - 1;
                if (compareAndSetState(c, nextc))
                    return nextc == 0;
            }
        }
    }
}
