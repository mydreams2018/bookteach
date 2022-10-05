package cn.kungreat.book.three.five;
/*
* 可见性 重排序
* volatile
*/
public class FiveMain {
    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //极端的一种演示情况
                while (running){

                }
                System.out.println(Thread.currentThread().getName()+":end");
            }
        },"A");
        thread.start();
        Thread.sleep(100);
        running=false;
        System.out.println(Thread.currentThread().getName()+":end");
    }
}
