package cn.kungreat.book.one.answer;

public class TestThread extends Thread{

    public TestThread(TestRunnable testRunnable) {
        super(testRunnable);
    }

    @Override
    public void run() {
        System.out.println("TestThread.执行了");
    }
}
