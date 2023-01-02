package cn.kungreat.book.seven.answer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class NoCapacity {

    private static final BlockingQueue<String> BLOCKING_QUEUE = new SynchronousQueue<>(true);

    public static void main(String[] args) throws Exception {
        BlockingRun blockingRun = new BlockingRun();
        new Thread(blockingRun).start();
        Thread.sleep(500);
        BLOCKING_QUEUE.add("one");
    }

    static final class BlockingRun implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println(BLOCKING_QUEUE.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
