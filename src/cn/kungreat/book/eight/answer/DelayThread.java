package cn.kungreat.book.eight.answer;

import java.util.concurrent.*;

/*
* 线程池使用有延迟效果和排序效果的阻塞队列
* */
public class DelayThread {

    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            1,1,20, TimeUnit.SECONDS, new DelayQueue());

    public static void main(String[] args) {
        THREAD_POOL.prestartAllCoreThreads();
        long currentTimeMillis = System.currentTimeMillis();
        THREAD_POOL.execute(new DelayedImpl(20,currentTimeMillis+3000));
        THREAD_POOL.execute(new DelayedImpl(5,currentTimeMillis+5000));
        THREAD_POOL.execute(new DelayedImpl(3,currentTimeMillis+10000));
        THREAD_POOL.execute(new DelayedImpl(80,currentTimeMillis+2000));
        THREAD_POOL.execute(new DelayedImpl(160,currentTimeMillis+2000));
    }

    static final class DelayedImpl implements Runnable, Delayed {
        private Integer comp;
        private Long delayTime;

        public DelayedImpl(Integer comp, Long delayTime) {
            this.comp = comp;
            this.delayTime = delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return delayTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            DelayedImpl delayed = (DelayedImpl) o;
            return comp - delayed.comp;
        }

        @Override
        public void run() {
            try {
                System.out.println(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "DelayedImpl{" +
                    "comp=" + comp +
                    '}';
        }

    }
}
