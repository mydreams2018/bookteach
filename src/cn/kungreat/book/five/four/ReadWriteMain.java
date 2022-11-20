package cn.kungreat.book.five.four;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteMain {
    static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    static final ReentrantReadWriteLock.ReadLock readLock = REENTRANT_READ_WRITE_LOCK.readLock();
    static final ReentrantReadWriteLock.WriteLock writeLock = REENTRANT_READ_WRITE_LOCK.writeLock();

    public static void main(String[] args) {
        RunnableMy runnableMy = new RunnableMy();
        Thread threadA = new Thread(runnableMy, "A");
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                runnableMy.writeTest();
            }
        }, "B");
        threadA.start();
        threadB.start();

    }

    static class RunnableMy implements Runnable{

        @Override
        public void run() {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }

        public void writeTest(){
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }
}
