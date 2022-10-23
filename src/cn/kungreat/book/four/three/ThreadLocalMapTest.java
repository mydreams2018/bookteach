package cn.kungreat.book.four.three;

/*
*  当一个对象不再被任何的字段、变量、常量引用的时候,此对象会被垃圾回收器回收.
*/
public class ThreadLocalMapTest {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static Object obj = new Object();

    public static void main(String[] args) {
        obj = null;//源对象所占用的内存空间将会被回收

        THREAD_LOCAL.set("有几个地方引用了此对象");

    }
}
