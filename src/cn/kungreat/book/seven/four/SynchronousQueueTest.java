package cn.kungreat.book.seven.four;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {

    private static final SynchronousQueue<String> SYNCHRONOUS_QUEUE =
                                            new SynchronousQueue<>(true);

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueueRun synchronousQueueRun = new SynchronousQueueRun();
        new Thread(synchronousQueueRun).start();
        Thread.sleep(1000);
        System.out.println(SYNCHRONOUS_QUEUE.take());
    }

    static final class SynchronousQueueRun implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("offer:"+SYNCHRONOUS_QUEUE.offer("one", 2, TimeUnit.SECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
