package cn.kungreat.book.nine.answer;

import java.util.concurrent.Semaphore;

/* 控制多线程并发数量为10 */

public class ThreeAnswer {
    static final Semaphore SEMAPHORE = new Semaphore(10);

    public static void main(String[] args) throws Exception {
        RunnableImpl runnable = new RunnableImpl();
        for (int i = 0; i < 50; i++) {
            new Thread(runnable).start();
        }
    }

    static final class RunnableImpl implements Runnable {

        @Override
        public void run() {
            try {
                SEMAPHORE.acquireUninterruptibly();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                SEMAPHORE.release();
            }
        }
    }
}
