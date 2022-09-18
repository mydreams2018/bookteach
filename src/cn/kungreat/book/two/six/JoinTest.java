package cn.kungreat.book.two.six;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start");
                    Thread.sleep(3000);//睡眠3秒
                    System.out.println("正常执行");
                } catch (InterruptedException e) {
                    System.out.println("中断");
                    System.out.println(Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        });
        thread.start();//启动线程

        Thread.currentThread().interrupt();//主线程中断
        thread.join();//主线程等待另一个线程对象销毁
        System.out.println("main-end");
    }
}
