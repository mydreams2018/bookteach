package cn.kungreat.book.five.four;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MethodMain {
    static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    static final ReentrantReadWriteLock.ReadLock readLock = REENTRANT_READ_WRITE_LOCK.readLock();
    static final ReentrantReadWriteLock.WriteLock writeLock = REENTRANT_READ_WRITE_LOCK.writeLock();

    public static void main(String[] args) throws InterruptedException {
        RunnableMy runnableMy = new RunnableMy();
        new Thread(runnableMy,"A").start();
        new Thread(runnableMy,"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                runnableMy.writeTest();
            }
        }, "C").start();
        runnableMy.writeTest();
    }

    static class RunnableMy implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                readLock.lock();
                System.out.println(Thread.currentThread().getName()+":run");
                //输出当前执行线程名称
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }

        public void writeTest(){
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+":writeTest");
                Thread.sleep(1500);
                System.out.println(REENTRANT_READ_WRITE_LOCK.getQueueLength());
                //返回等待获得读写锁的执行线程数量估计值
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }
}
