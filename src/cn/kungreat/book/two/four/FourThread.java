package cn.kungreat.book.two.four;

public class FourThread extends Thread{
    public FourThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public FourThread() {

    }

    @Override
    public void run() {
        int a = 5 / 0;
        System.out.println("FourThread");
    }
}
