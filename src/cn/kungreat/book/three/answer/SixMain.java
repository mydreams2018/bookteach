package cn.kungreat.book.three.answer;

public class SixMain {

    private volatile static boolean running = true;
    public static void main(String[] args) throws InterruptedException {
        StateRunnable stateRunnable = new StateRunnable();
        Thread thread = new Thread(stateRunnable,"A");
        thread.start();
        Thread.sleep(100);//睡眠100毫秒

        System.out.println(thread.getState());//线程状态
        running=false;
    }

    static class StateRunnable implements Runnable{

        @Override
        public void run() {
            while (running){

            }
            System.out.println(Thread.currentThread().getName()+":end");
        }
    }
}
