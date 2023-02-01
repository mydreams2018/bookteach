package cn.kungreat.book.nine.answer;

import java.util.concurrent.CountDownLatch;

/*主执行线程阻塞等待其它执行线程完成任务*/
public class OneAnswer {

    static final CountDownLatch COUNT = new CountDownLatch(2);

    public static void main(String[] args) throws Exception {
        RunnableImpl runnable = new RunnableImpl();
        new Thread(runnable,"A").start();
        new Thread(runnable,"B").start();
        COUNT.await();
        System.out.println("main-end");
    }

    static final class RunnableImpl implements Runnable{

        @Override
        public void run() {
            COUNT.countDown();
        }
    }
}
