package cn.kungreat.book.six.answer;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCAS {
    public static final AtomicInteger ATOMIC_INTEGER_ADD = new AtomicInteger(2);

    public static void main(String[] args) throws Exception {
        RunIncrement runIncrement = new RunIncrement();
        Thread thread = new Thread(runIncrement);
        thread.start();
        thread.join();
        System.out.println("原子:"+ATOMIC_INTEGER_ADD.get());
    }

    static final class RunIncrement implements Runnable{

        @Override
        public void run() {
            while (!ATOMIC_INTEGER_ADD.compareAndSet(ATOMIC_INTEGER_ADD.get(),10)){

            }
        }
    }

}
