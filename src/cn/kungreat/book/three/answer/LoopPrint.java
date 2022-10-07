package cn.kungreat.book.three.answer;
/*
*根据业务要求补全代码,A、B两个线程轮流打印数字,从1~100
*/
public class LoopPrint {

    public static void main(String[] args) throws InterruptedException {
        LoopRunnable loopRunnable = new LoopRunnable();
        Thread threadA = new Thread(loopRunnable, "A");
        Thread threadB = new Thread(loopRunnable, "B");
        threadA.start();
        threadB.start();
    }

    static class LoopRunnable implements Runnable{
        private volatile int num = 1;
        @Override
        public void run() {
            for (;num<=100;){
                synchronized (this){
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    num++;
                    this.notify();
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            synchronized (this){
                this.notify();
            }
        }
    }
}
