package cn.kungreat.book.six.two;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount {
    public static final AtomicInteger ATOMIC_INTEGER_ADD = new AtomicInteger();
    public static volatile int count;

    public static void main(String[] args) throws Exception {
        RunIncrement runIncrement = new RunIncrement();
        Thread thread = new Thread(runIncrement);
        Thread thread1 = new Thread(runIncrement);
        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println("原子:"+ATOMIC_INTEGER_ADD.get());
        System.out.println("count:"+count);
    }

    static final class RunIncrement implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                count++;
                ATOMIC_INTEGER_ADD.incrementAndGet();
            }
        }
    }

}
