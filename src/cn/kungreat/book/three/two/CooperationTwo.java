package cn.kungreat.book.three.two;

public class CooperationTwo implements Runnable{
    public static int num;

    @Override
    public void run() {
        for(int x=0;x<100000;x++){
            //同一个锁对象,同时只能有一个线程进入执行
            synchronized (CooperationTwo.class){
                num++;
            }
        }
    }

    public void run1(){
        for(int x=0;x<100000;x++){
            //同一个锁对象,同时只能有一个线程进入执行
            run2();
        }
    }

    public synchronized static void run2(){
      /*  synchronized (this){
            num++;
        }*/
        num++;
    }
}
