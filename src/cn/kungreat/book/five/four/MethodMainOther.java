package cn.kungreat.book.five.four;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MethodMainOther {
    static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    static final ReentrantReadWriteLock.ReadLock readLock = REENTRANT_READ_WRITE_LOCK.readLock();

    public static void main(String[] args) throws InterruptedException {
        RunnableMy runnableMy = new RunnableMy();
        new Thread(runnableMy,"A").start();
        new Thread(runnableMy,"B").start();
    }

    static class RunnableMy implements Runnable{
        @Override
        public void run() {
            readLock.lock();
            try {
                Thread.sleep(500);
                readTest();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }

        public void readTest(){
            readLock.lock();
            try {
                Thread.sleep(100);
                System.out.println(REENTRANT_READ_WRITE_LOCK.getReadLockCount());
                //获得此对象读锁的计数器
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }
}
