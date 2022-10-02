package cn.kungreat.book.three.four;

public class FourMain {

    public static void main(String[] args) throws InterruptedException {
        MonitorRunnable monitorRunnable = new MonitorRunnable();
        Thread thread = new Thread(monitorRunnable, "A");
        thread.start();
        new Thread(monitorRunnable,"B").start();

        Thread.sleep(2000);//睡眠2秒,保证A、B线程都能进入wait()
        synchronized (monitorRunnable){
            System.out.println(Thread.currentThread().getName());
//            monitorRunnable.notify();//唤醒正在等待此对象监视器上的单个线程
//            System.out.println("唤醒正在等待此对象监视器上的单个线程");
            monitorRunnable.notifyAll();
            System.out.println("唤醒正在等待此对象监视器上的所有线程");
        }
        System.out.println(Thread.currentThread().getName()+":end");
    }

    static class MonitorRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (this){
                System.out.println(Thread.currentThread().getName());
                try {
//                    Thread.sleep(5000);
                    this.wait();
                    System.out.println(Thread.currentThread().getName()+"被唤醒了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName()+":end");
        }
    }
}
