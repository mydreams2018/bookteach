package cn.kungreat.book.nine.one;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchOne {

    static final CountDownLatch startSignal = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        new Thread(new Worker(),"A").start();
        System.out.println("doSomethingElse");
        Thread.sleep(2000);
        startSignal.countDown();
    }

   static class Worker implements Runnable {

        public void run() {
            try {
                System.out.println(startSignal.await(1, TimeUnit.SECONDS));
                System.out.println(Thread.currentThread().getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
