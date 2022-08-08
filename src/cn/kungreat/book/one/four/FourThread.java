package cn.kungreat.book.one.four;

public class FourThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("FourThread 执行了");
    }
}
