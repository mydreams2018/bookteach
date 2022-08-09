package cn.kungreat.book.one.seven;

public class SevenThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        throw new RuntimeException("dfsdfsdf");
        System.out.println("SevenThread:isDaemon="+Thread.currentThread().isDaemon());//false
    }
}
