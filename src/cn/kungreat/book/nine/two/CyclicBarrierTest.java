package cn.kungreat.book.nine.two;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("成团");
        }
    });

    public static void main(String[] args) throws Exception {
        RunnableImpl runnable = new RunnableImpl();
        new Thread(runnable,"A").start();
        new Thread(runnable,"B").start();
        Thread.sleep(1000);
        Thread thread = new Thread(runnable, "C");
        thread.start();
    }

    static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            try {
//                if(Thread.currentThread().getName().equals("C")){
//                    Thread.currentThread().interrupt();
//                }
                System.out.println(CYCLIC_BARRIER.getNumberWaiting());
                CYCLIC_BARRIER.await();
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
