package cn.kungreat.book.three.one;

public class OneMain {

    public static void main(String[] args) throws InterruptedException {
        CooperationRunnable cooperationRunnable = new CooperationRunnable();

        Thread thread1 = new Thread(cooperationRunnable, "A");
        thread1.start();

        Thread thread2 = new Thread(cooperationRunnable, "B");
        thread2.start();

        Thread thread3 = new Thread(cooperationRunnable, "C");
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(cooperationRunnable.num);
    }

}
