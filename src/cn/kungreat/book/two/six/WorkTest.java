package cn.kungreat.book.two.six;

import java.util.Arrays;

public class WorkTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("A组");
        Thread thread = new Thread(threadGroup,new MyRunnable(),"A线程");
        thread.start();
        Thread.sleep(300);
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        System.out.println(Arrays.toString(threads));
    }

    static final class MyRunnable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
