package cn.kungreat.book.nine.answer;

import java.util.concurrent.CountDownLatch;

/*5个执行线程阻塞等待,直到主执行线程睡眠2秒后再唤醒5个阻塞等待的执行线程。*/

public class TwoAnswer {
    static final CountDownLatch COUNT = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        RunnableImpl runnable = new RunnableImpl();
        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(2000);
        COUNT.countDown();
        System.out.println("main-end");
    }

    static final class RunnableImpl implements Runnable {

        @Override
        public void run() {
            try {
                COUNT.await();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
