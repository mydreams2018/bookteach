package cn.kungreat.book.two.one;

import java.util.Arrays;

public class OneMain {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup threadGroup1 = new ThreadGroup("my-1");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1,"my-2");
        threadGroup2.setMaxPriority(2);
        Thread thread = new Thread(threadGroup2, "老唐");
        thread.start();
        System.out.println(thread.getPriority());
        System.out.println("threadGroup2:"+threadGroup2.getMaxPriority());
    }
}
