package cn.kungreat.book.one.seven;

public class SevenMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());//main
        System.out.println(Thread.currentThread().isDaemon());//false

        SevenThread sevenThread = new SevenThread();
//        sevenThread.setDaemon(true);
        sevenThread.start();

    }
}
