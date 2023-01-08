package cn.kungreat.book.eight.one;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            2,5,2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5)
            , new MyDiscardPolicy());

    public static void main(String[] args) throws InterruptedException {
//        THREAD_POOL.prestartCoreThread();
        for (int i = 0; i < 10; i++) {
            THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // ...实际业务中,需要处理interrupt
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        THREAD_POOL.allowCoreThreadTimeOut(true);
        Thread.sleep(500);
        System.out.println(THREAD_POOL.shutdownNow());
        System.out.println(THREAD_POOL.isShutdown());
        System.out.println(THREAD_POOL.isTerminating());
        Thread.sleep(2000);
        System.out.println(THREAD_POOL.isTerminated());
        System.out.println("main-end");
    }

    public static class MyDiscardPolicy implements RejectedExecutionHandler {

        public MyDiscardPolicy() { }

        /**
         * Does nothing, which has the effect of discarding task r.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("所有的容量已经满了,到了拒绝处理器了");
        }
    }
}
