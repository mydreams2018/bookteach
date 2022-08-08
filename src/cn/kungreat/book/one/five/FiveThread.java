package cn.kungreat.book.one.five;

public class FiveThread extends Thread{
    public FiveThread() {
    }

    public FiveThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //当前对象名称
        System.out.println("FiveThread:"+this.getName());
        //当前执行线程对象名称
        System.out.println("currentThread:"+Thread.currentThread().getName());

    }
}
