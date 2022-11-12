package cn.kungreat.book.five.three;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTestTwo {
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public void testTime()  {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.println(condition.awaitNanos(10000000000L));//10秒
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



    public static void main(String[] args) throws InterruptedException {
        ConditionTestTwo conditionTest = new ConditionTestTwo();
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
