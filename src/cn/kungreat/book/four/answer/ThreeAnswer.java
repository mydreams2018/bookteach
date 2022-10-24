package cn.kungreat.book.four.answer;

public class ThreeAnswer {

    public static ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        THREAD_LOCAL.set("线程局部变量");
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable,"B").start();
        Thread.sleep(1000);
        THREAD_LOCAL = new ThreadLocal<>();
        System.out.println(Thread.currentThread().getName()+":"+THREAD_LOCAL.get());
    }

    static final class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+THREAD_LOCAL.get());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+":"+THREAD_LOCAL.get());
        }
    }
}
