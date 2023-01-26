package cn.kungreat.book.nine.one;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    static final CountDownLatch startSignal = new CountDownLatch(1);
    static final CountDownLatch doneSignal = new CountDownLatch(10);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; ++i) {
            new Thread(new Worker()).start();
        }
        System.out.println("doSomethingElse");
        Thread.sleep(2000);
        startSignal.countDown();      // let all threads proceed
        doneSignal.await();           // wait for all to finish
        System.out.println("main:"+doneSignal.getCount());
    }

   static class Worker implements Runnable {

        public void run() {
            try {
                startSignal.await();
                System.out.println(Thread.currentThread().getName());
                doWork();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        synchronized static void doWork() {
            System.out.println(doneSignal.getCount());
            doneSignal.countDown();
        }
    }

}
