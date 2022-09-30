package cn.kungreat.book.three.two;

public class TwoMain3 {
    /*
     * synchronized对象锁特性
     * 3. 可重入
     * 4. 不响应中断
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                synchronized (this){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName());
                    reentry();//可重入,同一个锁对象
                }
            }

            public synchronized void reentry(){
                System.out.println(Thread.currentThread().getName()+":reentry");
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
