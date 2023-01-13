package cn.kungreat.book.eight.three;

import java.util.List;
import java.util.concurrent.*;

public class AbstractExecutorServiceTestTwo {
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            2,5,2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
    public static void main(String[] args) throws Exception {

        Callable<String> callableOne = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                int ax = 1 / 0;
                return "one";
            }
        };

        Callable<String> callableTwo = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                int ax = 1 / 0;
                return "two";
            }
        };

        Callable<String> callableThree = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "three";
            }
        };

        List<Callable<String>> callableList = List.of(callableOne,callableTwo,callableThree);
        List<Future<String>> futures = THREAD_POOL.invokeAll(callableList);

        for (int i = 0; i < futures.size(); i++) {
            try {
                System.out.println(futures.get(i).get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
