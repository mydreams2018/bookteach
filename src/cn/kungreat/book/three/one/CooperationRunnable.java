package cn.kungreat.book.three.one;

public class CooperationRunnable implements Runnable{

    public int num;
    @Override
    public void run() {
        for(int x=0;x<100000;x++){
            //同一个锁对象,同时只能有一个线程进入执行
            synchronized (this){
                num++;
            }
        }
    }

}
