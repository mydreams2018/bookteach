package cn.kungreat.book.three.answer;

/*
 * 启动三个线程A、B、C每个线程将自己的名称轮流打印5遍,打印顺序是ABCABC…
 * */
public class ThreePrint {

    public static void main(String[] args) {
        LoopRunnable loopRunnable = new LoopRunnable();
        new Thread(loopRunnable, "A").start();
        new Thread(loopRunnable, "B").start();
        new Thread(loopRunnable, "C").start();
    }

    static class LoopRunnable implements Runnable {
        private volatile int loopIndex = 0;
        private final String[] loopNames = {"A", "B", "C"};

        @Override
        public void run() {
            for (int x = 0; x < 5; x++) {
                synchronized (this) {
                    String name = Thread.currentThread().getName();
                    //名称不匹配时一直循环
                    while (!name.equals(loopNames[loopIndex])) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print(name);//消费名称
                    loopIndex++;
                    if (loopIndex == loopNames.length) {
                        loopIndex = 0;//重置指针
                    }
                    this.notifyAll();
                }
            }
        }
    }
}
