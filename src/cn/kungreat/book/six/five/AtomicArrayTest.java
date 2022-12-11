package cn.kungreat.book.six.five;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {

    private static final AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(16);

    public static void main(String[] args) {
        ATOMIC_INTEGER_ARRAY.set(6,100);
        System.out.println(ATOMIC_INTEGER_ARRAY.get(6));
        System.out.println(ATOMIC_INTEGER_ARRAY.get(15));
        System.out.println(ATOMIC_INTEGER_ARRAY.compareAndSet(6, 100, 200));
        System.out.println(ATOMIC_INTEGER_ARRAY.get(6));
    }
}
