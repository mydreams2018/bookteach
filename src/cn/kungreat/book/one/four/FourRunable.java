package cn.kungreat.book.one.four;

public class FourRunable implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("FourRunable 执行了");
    }
}
