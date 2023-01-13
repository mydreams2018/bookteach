package cn.kungreat.book.eight.three;

import java.util.Random;
import java.util.concurrent.*;

public class AbstractExecutorServiceTest {
    private static final Random RANDOM = new Random();
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            2,5,2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
    public static void main(String[] args) throws Exception {
        Future<Integer> submit = THREAD_POOL.submit(new RunnableAdapter(new RunnableImpl()));

        System.out.println(Thread.currentThread().getName()+":"+submit.get());

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

    private static final class RunnableAdapter implements Callable<Integer> {
        private final Runnable task;
        RunnableAdapter(Runnable task) {
            this.task = task;
        }
        public Integer call() {
            task.run();
            return RANDOM.nextInt();
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + task + "]";
        }
    }

}
