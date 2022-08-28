package cn.kungreat.book.two.one;

public class OneMain {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getThreadGroup());

        ThreadGroup threadGroup1 = new ThreadGroup("my-1");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1,"my-2");


        ThreadGroup parent = threadGroup2.getParent();
        while (parent != null){
            System.out.println(parent);
            parent = parent.getParent();
        }

    }
}
