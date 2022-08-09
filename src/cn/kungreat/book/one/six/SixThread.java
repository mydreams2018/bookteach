package cn.kungreat.book.one.six;

public class SixThread extends Thread{
    public SixThread() {
    }

    public SixThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("level:"+this.getPriority());
    }
}
