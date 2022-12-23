package cn.kungreat.book.seven.two;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    private static final LinkedBlockingQueue<String> BLOCKING_QUEUE = new LinkedBlockingQueue<>(8);

    public static void main(String[] args) throws InterruptedException {
        BLOCKING_QUEUE.put("one");
        BLOCKING_QUEUE.put("two");
//        System.out.println(BLOCKING_QUEUE.take());
//        System.out.println(BLOCKING_QUEUE.element());
//        System.out.println(BLOCKING_QUEUE.contains("one"));
//        System.out.println(BLOCKING_QUEUE.drainTo(new ArrayList<>()));
//        System.out.println(BLOCKING_QUEUE.peek());
        System.out.println(BLOCKING_QUEUE.remove("tere"));

        System.out.println(BLOCKING_QUEUE.remainingCapacity());
        System.out.println(BLOCKING_QUEUE.removeIf((e) -> {
            System.out.println("removeIf:"+e);
            return false;
        }));
        BLOCKING_QUEUE.forEach(System.out::println);
    }
}
