package cn.kungreat.book.eight.one;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolLead {

    private static final BlockingQueue<QueueRun> BLOCKING_QUEUE = new ArrayBlockingQueue<>(64);

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        new Thread(new LoopRun(),"A").start();
        QueueRun one = new QueueRun("one");
        BLOCKING_QUEUE.add(one);
        BLOCKING_QUEUE.add(new QueueRun("two"));
        BLOCKING_QUEUE.add(new QueueRun("three"));
        System.out.println(one.getResult());
        System.out.println("main-end");
    }

    static final class LoopRun implements Runnable{

        @Override
        public void run() {
                try {
                    while (true){
                        QueueRun take = BLOCKING_QUEUE.take();
                        take.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

    static final class QueueRun implements Runnable{

        private String name;

        private Integer rt;

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public QueueRun(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                Thread.sleep(RANDOM.nextInt(5000));
                System.out.println(Thread.currentThread().getName()+":"+this.name);
                rt = RANDOM.nextInt(10000);
            } catch (InterruptedException e) {
                rt = -1;
                e.printStackTrace();
            } finally {
                condition.signalAll();
                lock.unlock();
            }
        }

        public Integer getResult(){
            lock.lock();
            try {
                while (rt == null){
                    condition.await();
                }
                return rt;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }
    }
}
