package cn.kungreat.book.three.two;

public class TwoMain {
    public static void main(String[] args) throws InterruptedException {
        CooperationTwo cooperationTwo = new CooperationTwo();
        Thread thread1 = new Thread(cooperationTwo, "A");
        thread1.start();//启动线程

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cooperationTwo.run1();
            }
        },"B");
        thread2.start();//启动线程

        for(int x=0;x<100000;x++){
            //同一个锁对象,同时只能有一个线程进入执行
            synchronized (CooperationTwo.class){
                CooperationTwo.num++;
            }
        }

        thread1.join();//等待thread1线程对象锁毁
        thread2.join();//等待thread2线程对象锁毁

        System.out.println(CooperationTwo.num);
    }
}
