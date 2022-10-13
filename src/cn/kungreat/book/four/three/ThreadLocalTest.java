package cn.kungreat.book.four.three;

public class ThreadLocalTest {

    public static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(()->{
        System.out.println("重写initialValue");
        return  "AAA";
    });

    public static void main(String[] args) {
        THREAD_LOCAL.set("main");//设置线程局部变量数据
        Thread thread = Thread.currentThread();
        THREAD_LOCAL.remove();//删除此KEY数据
        System.out.println(THREAD_LOCAL.get());
    }

}
