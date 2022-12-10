package cn.kungreat.book.six.four;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    private static final AtomicIntegerFieldUpdater<BaseInt> FIELD_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(BaseInt.class,"num");

    public static void main(String[] args) {
        BaseInt baseInt = new BaseInt();
        FIELD_UPDATER.set(baseInt,100);
        System.out.println(FIELD_UPDATER.get(baseInt));

    }

    static final class BaseInt {
        public volatile int num;
    }
}
