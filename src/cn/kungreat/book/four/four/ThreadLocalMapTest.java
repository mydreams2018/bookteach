package cn.kungreat.book.four.four;

/*
*  当一个对象不再被任何的字段、变量、常量引用的时候,此对象会被垃圾回收器回收.
*/
public class ThreadLocalMapTest {

    public static ThreadLocal<String> THREAD_LOCAL = null;

    public static Object obj = new Object();

    public static void main(String[] args) {
        obj = null;//源对象所占用的内存空间将会被回收

        /*
        *
        *  前4个只有 ThreadLocalMap table 引用
        *  第5个 THREAD_LOCAL=@704  ThreadLocalMap table引用 @704
        */
        for (int x=1;x<6;x++){
            THREAD_LOCAL = new ThreadLocal<>();
            THREAD_LOCAL.set(String.valueOf(x));
        }
        Thread currentThread = Thread.currentThread();
        /*
         * 有二处引用
        *  THREAD_LOCAL字段、ThreadLocalMap对象的table字段
        */
        System.gc();//手动调用垃圾回收器
        System.out.println(THREAD_LOCAL.get());
    }
}
