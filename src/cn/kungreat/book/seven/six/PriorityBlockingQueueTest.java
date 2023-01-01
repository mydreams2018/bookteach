package cn.kungreat.book.seven.six;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

    private static final PriorityBlockingQueue<PriorityImpl> PRIORITY_BLOCKING_QUEUE =
                                                    new PriorityBlockingQueue<>(16, (o1, o2) -> o1.comp - o2.comp);

    public static void main(String[] args) throws Exception {
        PRIORITY_BLOCKING_QUEUE.add(new PriorityImpl(15));
        PRIORITY_BLOCKING_QUEUE.add(new PriorityImpl(2));
        PRIORITY_BLOCKING_QUEUE.add(new PriorityImpl(66));
        PRIORITY_BLOCKING_QUEUE.add(new PriorityImpl(78));

        System.out.println(PRIORITY_BLOCKING_QUEUE.take());
        System.out.println(PRIORITY_BLOCKING_QUEUE.take());
        System.out.println(PRIORITY_BLOCKING_QUEUE.take());
        System.out.println(PRIORITY_BLOCKING_QUEUE.take());
    }

    static final class PriorityImpl {

        private Integer comp;

        public PriorityImpl(Integer comp) {
            this.comp = comp;
        }

        @Override
        public String toString() {
            return "PriorityImpl{" +
                    "comp=" + comp +
                    '}';
        }
    }
}
