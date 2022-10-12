package cn.kungreat.book.four.three;

/*
 * 线程局部变量
 * 数据保存在Thread对象threadLocals、inheritableThreadLocals字段中,所以数据是隔离的并发安全.
 *
 * 共享线程局部变量
 *              源 当前执行线程对象  目标 新new的线程对象
 *                  main               A
 */
public class ThreeShareMain {

    public static final InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set("test");//设置线程局部变量数据
        ThreadLocalsRun threadLocalsRun = new ThreadLocalsRun();
        new Thread(Thread.currentThread().getThreadGroup(),threadLocalsRun, "A",
                0, false).start();
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName()+":"+THREAD_LOCAL.get());//获得线程局部变量数据
    }

    static class ThreadLocalsRun implements Runnable{

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName()+":"+THREAD_LOCAL.get());//获得线程局部变量数据
        }
    }
}
