package cn.kungreat.book.four.three;

/*
 * 线程局部变量
 * 数据保存在Thread对象threadLocals、inheritableThreadLocals字段中,所以数据是隔离的并发安全.
 *
 */
public class ThreeMain {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set("test");
        ThreadLocalsRun threadLocalsRun = new ThreadLocalsRun();
        new Thread(threadLocalsRun, "A").start();
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName()+":"+THREAD_LOCAL.get());
    }

    static class ThreadLocalsRun implements Runnable{

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName()+":"+THREAD_LOCAL.get());
        }
    }
}
