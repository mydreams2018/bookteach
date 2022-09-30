package cn.kungreat.book.three.two;

public class TwoMain2 {
    /*
     * synchronized对象锁特性
     * 1. 异常自动释放锁
     * 2. 非公平锁
     * 3. 可重入
     * 4. 不响应中断
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this){
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    int num = 10 / 0;
                    System.out.println(Thread.currentThread().getName()+"-end");
                }

            }
        };
        Thread thread = new Thread(runnable,"A");
        thread.start();

        Thread.sleep(100);
        synchronized (runnable){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
