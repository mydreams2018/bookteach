package cn.kungreat.book.six.two;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

public class AtomicInt {

    public static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(10);
    public static final AtomicInteger ATOMIC_INTEGER_ADD = new AtomicInteger();


    public static void main(String[] args) throws Exception {
        System.out.println(ATOMIC_INTEGER.accumulateAndGet(10, (left, right) -> left + right));//20
        System.out.println(ATOMIC_INTEGER.addAndGet(10));//30
//        System.out.println(ATOMIC_INTEGER.decrementAndGet());//29
        System.out.println(ATOMIC_INTEGER.getAndDecrement());//30

        System.out.println(ATOMIC_INTEGER.updateAndGet((left) -> left + 100));//129

        RunIncrement runIncrement = new RunIncrement();
        Thread thread = new Thread(runIncrement);
        thread.start();
        Thread thread1 = new Thread(runIncrement);
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println("add:"+ATOMIC_INTEGER_ADD.get());
    }

    static final class RunIncrement implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                ATOMIC_INTEGER_ADD.incrementAndGet();
            }
        }
    }

}
