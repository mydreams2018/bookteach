package cn.kungreat.book.one.six;

public class SixMain {

    public static void main(String[] args) {
        System.out.println("main:"+Thread.currentThread().getPriority());
        SixThread sixThread = new SixThread("通过构造方法-名称");
        sixThread.setPriority(Thread.MAX_PRIORITY);
        sixThread.start();

    }
}
