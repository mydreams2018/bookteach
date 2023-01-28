package cn.kungreat.book.nine.three;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final Semaphore SEMAPHORE = new Semaphore(2);

    public static void main(String[] args) throws Exception {
        RunnableImpl runnable = new RunnableImpl();
        new Thread(runnable,"A").start();
        Thread.sleep(100);
//        System.out.println(SEMAPHORE.drainPermits());
        new Thread(runnable,"B").start();
        Thread thread = new Thread(runnable, "C");
        thread.start();
        new Thread(runnable,"D").start();
        Thread.sleep(3000);
        System.out.println(SEMAPHORE.availablePermits());
    }

    static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            if(SEMAPHORE.tryAcquire()){
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    SEMAPHORE.release();
                }
            }else{
                System.out.println("no Semaphore");
            }
        }
    }
}
