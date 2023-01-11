package cn.kungreat.book.eight.two;


import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {

    private static final FutureTask<Integer> FUTURE_TASK =
                        new FutureTask<>(new RunnableImpl(),100);

    public static void main(String[] args) throws Exception {
        new Thread(FUTURE_TASK,"A").start();
        new Thread(() -> {
            try {
                System.out.println(FUTURE_TASK.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
        Thread.sleep(500);
        FUTURE_TASK.cancel(false);
        System.out.println("main-end");
    }

    static class RunnableImpl implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("RunnableImpl:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
