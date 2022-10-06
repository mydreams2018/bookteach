package cn.kungreat.book.three.six;

public class SixMain {

    public static void main(String[] args) throws InterruptedException {
        StateRunnable stateRunnable = new StateRunnable();
        Thread thread = new Thread(stateRunnable,"A");
        thread.start();
        Thread.sleep(100);//睡眠100毫秒
        synchronized (stateRunnable){
            System.out.println(thread.getState());//线程状态
            stateRunnable.notify();//唤醒正在等待此对象监视器上的单个线程
        }
    }

    static class StateRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (this){
                try {
                    this.wait(2000);
                    //使当前线程阻塞等待,直到它被唤醒或中断、或者直到经过一定量的实时时间,具有释放当前锁的特性
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
