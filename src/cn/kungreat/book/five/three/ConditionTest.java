package cn.kungreat.book.five.three;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest<E> {

    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
            System.out.println(Thread.currentThread().getName()+"put:"+x);
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            E x = (E) items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest<Integer> conditionTest = new ConditionTest<>();
        Random random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(1000);
                        int i = random.nextInt(9999);
                        conditionTest.put(i);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println(Thread.currentThread().getName()+":"+conditionTest.take());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println(Thread.currentThread().getName()+":"+conditionTest.take());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"C").start();
    }
}
