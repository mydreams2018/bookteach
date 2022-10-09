package cn.kungreat.book.four.two;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
* ThreadLocal 线程局部变量
*/
public class TwoMain {

    //LINK 类似Map结构里的key
    public static final ThreadLocal<Object> LINK = new ThreadLocal<>();
    public static final ThreadLocal<Object> LINKTWO = new ThreadLocal<>();

    public static void main(String[] args) {
        LinkRunnable linkRunnable = new LinkRunnable();
        new Thread(linkRunnable).start();
    }

    static class LinkRunnable implements Runnable{
        private static final Random RANDOM = new Random();
        @Override
        public void run() {
            LINK.set(RANDOM.nextInt());
            LINKTWO.set("LINKTWO");
            LinkOne.one();
        }
    }
}
