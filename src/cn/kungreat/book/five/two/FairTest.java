package cn.kungreat.book.five.two;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class FairTest {

    private static final ReentrantLockMy LOCK = new ReentrantLockMy(true);

    public static void main(String[] args) throws InterruptedException {
        RunnableMy runnableMy = new RunnableMy();
        for (int i = 1; i < 6; i++) {
            new Thread(runnableMy,"线程"+i).start();
            Thread.sleep(100);//保证线程启动的顺序
        }
    }

    static class RunnableMy implements Runnable{

        @Override
        public void run() {
            //循环二次公平锁拿锁时需要排队,非公平锁可以直接拿锁
            for (int i = 0; i < 2; i++) {
                LOCK.lock();//拿锁
                try {
                    Thread.sleep(1500);
                    System.out.println(LOCK.getQueuedThreads());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();//释放锁
                }
            }
        }
    }

    static final class ReentrantLockMy extends ReentrantLock{
        public ReentrantLockMy(boolean fair) {
            super(fair);
        }


        @Override
        protected Collection<Thread> getQueuedThreads() {
            return super.getQueuedThreads();
        }
    }
}
