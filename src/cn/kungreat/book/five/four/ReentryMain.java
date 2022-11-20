package cn.kungreat.book.five.four;

import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
* 可重入特性
* 持有写锁时 -> 可以再获得读锁
* 持有读锁时 -> 不可以再获得写锁
*
*/
public class ReentryMain {
    static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    static final ReentrantReadWriteLock.ReadLock readLock = REENTRANT_READ_WRITE_LOCK.readLock();
    static final ReentrantReadWriteLock.WriteLock writeLock = REENTRANT_READ_WRITE_LOCK.writeLock();

    public static void main(String[] args) {
        RunnableMy runnableMy = new RunnableMy();
        runnableMy.run();
    }

    static class RunnableMy implements Runnable{

        @Override
        public void run() {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+":run");
                //输出当前执行线程名称
                writeTest();
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
                //输出当前执行线程名称
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }
}
