package cn.kungreat.book.seven.answer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityAnswer {

    private static final BlockingQueue<Integer> QUEUE = new PriorityBlockingQueue<>();

    public static void main(String[] args) throws Exception {
        QUEUE.add(50);
        QUEUE.add(100);
        QUEUE.add(2);
        QUEUE.add(456);
        QUEUE.add(6);
        System.out.println(QUEUE.take());
        System.out.println(QUEUE.take());
        System.out.println(QUEUE.take());
        System.out.println(QUEUE.take());
        System.out.println(QUEUE.take());
    }

}
