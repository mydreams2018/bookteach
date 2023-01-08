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
        for (int i = 0; i < 10; i++) {
            THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // ...实际业务中,需要处理interrupt
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName()+":InterruptedException");
                    }
                }
            });
        }
        Thread.sleep(500);
        System.out.println(THREAD_POOL.shutdownNow());
        System.out.println("isShutdown:"+THREAD_POOL.isShutdown());
        System.out.println("isTerminating:"+THREAD_POOL.isTerminating());
        Thread.sleep(6000);
        System.out.println("isTerminated:"+THREAD_POOL.isTerminated());
        System.out.println("main-end");
    }

    public static class MyDiscardPolicy implements RejectedExecutionHandler {

        public MyDiscardPolicy() { }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("所有的容量已经满了,到了拒绝处理器了");
        }
    }
}
