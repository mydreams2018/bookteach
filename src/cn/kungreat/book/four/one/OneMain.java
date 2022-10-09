package cn.kungreat.book.four.one;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
* ThreadLocal 线程局部变量
*/
public class OneMain {

    public static void main(String[] args) {
        LinkRunnable linkRunnable = new LinkRunnable();
        new Thread(linkRunnable).start();
    }

    static class LinkRunnable implements Runnable{
        private static final Random RANDOM = new Random();
        /*
        * 方法链路传递数据,线程并发安全、代码臃肿、耦合度高、后期难维护
        */
        @Override
        public void run() {
            Map map = new HashMap<>();
            map.put("LINK",RANDOM.nextInt());
            LinkOne.one(map);
        }
    }
}
