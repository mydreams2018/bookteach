package cn.kungreat.book.seven.three;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueueTest {

    private static final LinkedTransferQueue<String> TRANSFER_QUEUE = new LinkedTransferQueue<>();

    public static void main(String[] args) throws InterruptedException {
//        TRANSFER_QUEUE.add("one");
//        TRANSFER_QUEUE.put("two");
//        TRANSFER_QUEUE.offer("three");
//        TRANSFER_QUEUE.tryTransfer("one");
//        TRANSFER_QUEUE.tryTransfer("two");
//        TRANSFER_QUEUE.tryTransfer("three");
//        System.out.println(TRANSFER_QUEUE.poll());
        TransferRun transferRun = new TransferRun();
        new Thread(transferRun).start();
        System.out.println(Thread.currentThread().getName()+":"+TRANSFER_QUEUE.take());
//        TRANSFER_QUEUE.tryTransfer("one",2, TimeUnit.SECONDS);
//        System.out.println(TRANSFER_QUEUE.poll());
    }

    static final class TransferRun implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(TRANSFER_QUEUE.getWaitingConsumerCount());
                TRANSFER_QUEUE.tryTransfer("one",1,TimeUnit.SECONDS);
                System.out.println(TRANSFER_QUEUE.getWaitingConsumerCount());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
