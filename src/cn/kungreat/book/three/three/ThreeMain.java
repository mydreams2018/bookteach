package cn.kungreat.book.three.three;

public class ThreeMain {
/*
* 线程死锁的产生
* A线程 拿锁objA 再去调deadLock()方法 阻塞等待拿锁objB
* 主线程 拿锁objB 再去调run()方法     阻塞等待拿锁objA
* 产生死锁
* */
    public static void main(String[] args) {
        DeadRunnable deadRunnable = new DeadRunnable();
        Thread thread = new Thread(deadRunnable,"A");
        thread.start();

        deadRunnable.deadLock();

    }

    static class DeadRunnable implements Runnable{
        private Object objA = new Object();
        private Object objB = new Object();

        @Override
        public void run() {
            synchronized (objA){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                deadLock();
            }
        }

        public void deadLock(){
            synchronized (objB){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                run();
            }
        }
    }
}
