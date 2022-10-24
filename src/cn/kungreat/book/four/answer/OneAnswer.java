package cn.kungreat.book.four.answer;

public class OneAnswer {

    public static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set("线程局部变量");
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable,"A").start();
        System.out.println(Thread.currentThread().getName()+":"+THREAD_LOCAL.get());
    }

    static final class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+THREAD_LOCAL.get());
        }
    }
}
