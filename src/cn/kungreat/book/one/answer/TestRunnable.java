package cn.kungreat.book.one.answer;

public class TestRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Runnable.执行了");
    }
}
