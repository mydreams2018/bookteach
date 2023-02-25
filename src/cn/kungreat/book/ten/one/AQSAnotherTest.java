package cn.kungreat.book.ten.one;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSAnotherTest {
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public void testTime()  {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            condition.await();
            System.out.println(Thread.currentThread().getName()+":end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void test()  {
        lock.lock();
        try {
            System.out.println(System.currentTimeMillis());//当前系统时间的毫秒数
            Thread.sleep(3000);
            System.out.println(System.currentTimeMillis());//当前系统时间的毫秒数
            condition.signal();//唤醒单个等待此Condition对象的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws Exception {
        AQSAnotherTest conditionTest = new AQSAnotherTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionTest.testTime();
            }
        }, "A").start();
        Thread.sleep(200);
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionTest.test();
            }
        }, "B").start();
    }

}
