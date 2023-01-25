package cn.kungreat.book.eight.answer;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/*
* 定时任务首次延迟时间10秒、间隔执行时间3秒
* */
public class ScheduledThread {

    static final ScheduledThreadPoolExecutor SCHEDULED_POOL =
            new ScheduledThreadPoolExecutor(2);

    public static void main(String[] args) {
        SCHEDULED_POOL.scheduleAtFixedRate(new RunnableImpl(),10,3, TimeUnit.SECONDS);
    }

    static class RunnableImpl implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
