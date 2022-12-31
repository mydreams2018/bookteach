package cn.kungreat.book.seven.five;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    private static final DelayQueue<DelayedImpl> DELAY_QUEUE = new DelayQueue<>();

    public static void main(String[] args) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        DELAY_QUEUE.add(new DelayedImpl(20,currentTimeMillis+3000));
        DELAY_QUEUE.add(new DelayedImpl(5,currentTimeMillis+5000));
        DELAY_QUEUE.add(new DelayedImpl(3,currentTimeMillis+10000));
        DELAY_QUEUE.add(new DelayedImpl(80,currentTimeMillis+2000));
        DELAY_QUEUE.add(new DelayedImpl(160,currentTimeMillis+2000));

        System.out.println(DELAY_QUEUE.take());
        System.out.println(DELAY_QUEUE.take());
        System.out.println(DELAY_QUEUE.take());
        System.out.println(DELAY_QUEUE.take());
        System.out.println(DELAY_QUEUE.take());
        System.out.println(DELAY_QUEUE.remove());
    }

    static final class DelayQueueRun implements Runnable{

        @Override
        public void run() {

        }
    }

    static final class DelayedImpl implements Delayed {

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
        public String toString() {
            return "DelayedImpl{" +
                    "comp=" + comp +
                    '}';
        }
    }
}
