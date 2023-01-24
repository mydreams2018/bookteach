package cn.kungreat.book.eight.four;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTest {

    static final ScheduledThreadPoolExecutor SCHEDULED_POOL =
            new ScheduledThreadPoolExecutor(2);

    public static void main(String[] args) {
        SCHEDULED_POOL.schedule(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return null;
            }
        }, 5, TimeUnit.SECONDS);
    }

    static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            try {
//                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
