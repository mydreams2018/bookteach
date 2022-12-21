package cn.kungreat.book.seven.one;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    private static final ArrayBlockingQueue<String> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(8);

    public static void main(String[] args) throws InterruptedException {
        ARRAY_BLOCKING_QUEUE.put("one");
        ARRAY_BLOCKING_QUEUE.put("two");
//        System.out.println(ARRAY_BLOCKING_QUEUE.take());
//        System.out.println(ARRAY_BLOCKING_QUEUE.element());
//        System.out.println(ARRAY_BLOCKING_QUEUE.contains("one"));
//        System.out.println(ARRAY_BLOCKING_QUEUE.drainTo(new ArrayList<>()));
//        System.out.println(ARRAY_BLOCKING_QUEUE.peek());
        System.out.println(ARRAY_BLOCKING_QUEUE.remove("tere"));

        System.out.println(ARRAY_BLOCKING_QUEUE.remainingCapacity());
        System.out.println(ARRAY_BLOCKING_QUEUE.removeIf((e) -> {
            System.out.println("removeIf:"+e);
            return false;
        }));
        ARRAY_BLOCKING_QUEUE.forEach(System.out::println);
    }
}
