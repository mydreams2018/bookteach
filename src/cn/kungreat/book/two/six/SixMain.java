package cn.kungreat.book.two.six;

public class SixMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int num = 0;
                while (true){
                    Thread.yield();
                    System.out.println("...执行了第"+num+"次任务");
                    num++;
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("中断此任务,保存必要的数据");
                        return;
                    }
                }
//                System.out.println("one:"+Thread.currentThread().isInterrupted());
//                System.out.println("one:"+Thread.currentThread().isInterrupted());
//
//                System.out.println("one:"+Thread.interrupted());
//                System.out.println("one:"+Thread.interrupted());
            }
        },"one");
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();


        System.out.println("main:"+Thread.currentThread().isInterrupted());
        System.out.println("main:"+Thread.currentThread().isInterrupted());
    }
}
