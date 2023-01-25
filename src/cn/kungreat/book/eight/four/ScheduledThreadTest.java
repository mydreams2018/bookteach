package cn.kungreat.book.eight.four;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTest {

    static final ScheduledThreadPoolExecutor SCHEDULED_POOL =
            new ScheduledThreadPoolExecutor(2);

    public static void main(String[] args) {
//        SCHEDULED_POOL.scheduleAtFixedRate(new RunnableImpl(), 5,2, TimeUnit.SECONDS);

        //scheduleAtFixedRate   1000  1005  1007 1009 1011

//        SCHEDULED_POOL.scheduleWithFixedDelay(new RunnableImpl(), 5,2, TimeUnit.SECONDS);
        //scheduleWithFixedDelay   1000  1005+3 1010+3 1015+3

        SCHEDULED_POOL.submit(new RunnableImpl());
    }

    static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
