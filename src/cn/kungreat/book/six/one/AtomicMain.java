package cn.kungreat.book.six.one;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicMain {

    private static final AtomicBoolean ATOMIC_BOOLEAN = new AtomicBoolean();

    public static void main(String[] args) throws InterruptedException {
//        AtomicRun atomicRun = new AtomicRun();
//        new Thread(atomicRun,"A").start();
//        Thread.sleep(1000);
//        atomicRun.setPlain();
        System.out.println(ATOMIC_BOOLEAN.get());
        System.out.println("cas:"+ATOMIC_BOOLEAN.compareAndExchange(false, true));
        System.out.println(ATOMIC_BOOLEAN.get());
    }

    static final class AtomicRun implements Runnable{

        @Override
        public void run() {
            while (!ATOMIC_BOOLEAN.getOpaque()){

            }
            System.out.println(Thread.currentThread().getName()+":end");
        }

        public void setPlain(){
            ATOMIC_BOOLEAN.setOpaque(true);
        }

    }
}
