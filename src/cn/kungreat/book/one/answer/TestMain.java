package cn.kungreat.book.one.answer;

public class TestMain {

    public static void main(String[] args) {
        TestRunnable testRunnable = new TestRunnable();
        TestThread testThread = new TestThread(testRunnable);
        testThread.start();
    }
}
