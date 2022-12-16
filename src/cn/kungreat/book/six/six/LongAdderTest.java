package cn.kungreat.book.six.six;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    private static final LongAdder LONG_ADDER = new LongAdder();

    public static void main(String[] args) {
        LONG_ADDER.add(100);
        LONG_ADDER.add(100);
        LONG_ADDER.add(100);
        System.out.println(LONG_ADDER.sum());//300
    }
}
