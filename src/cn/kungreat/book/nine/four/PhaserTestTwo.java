package cn.kungreat.book.nine.four;

import java.util.concurrent.Phaser;

public class PhaserTestTwo {

    private static final Phaser PHASER = new Phaser(5);

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("awaitAdvance:"+PHASER.awaitAdvance(0));
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        PHASER.arriveAndAwaitAdvance();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
