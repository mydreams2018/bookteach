package cn.kungreat.book.two.five;

public class FiveMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A任务开始...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A任务结束...");
            }
        });
        thread.start();

        System.out.println("B任务开始...");
        thread.join();
        System.out.println("B任务结束...");
    }

}
