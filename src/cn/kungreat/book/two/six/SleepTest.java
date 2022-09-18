package cn.kungreat.book.two.six;

public class SleepTest {
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

        Thread.sleep(1000);//睡眠1秒
        thread.interrupt();//中断此线程对象

    }
}
