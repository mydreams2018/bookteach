package cn.kungreat.book.nine.four;

import java.util.concurrent.Phaser;

public class PhaserTest {

    private static final Phaser PHASER = new MyPhaser(5);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PHASER.arriveAndAwaitAdvance();
                }
            }).start();
        }
        Thread.sleep(2000);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PHASER.arriveAndAwaitAdvance();
                }
            }).start();
        }
        Thread.sleep(2000);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PHASER.arriveAndAwaitAdvance();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("isTerminated:1-"+PHASER.isTerminated());
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PHASER.arriveAndAwaitAdvance();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("isTerminated:2-"+PHASER.isTerminated());
    }

    static final class MyPhaser extends Phaser{

        public MyPhaser(int parties) {
            super(parties);
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("registeredParties:"+registeredParties);
            switch (phase) {
                case 0 -> System.out.println("开始");
                case 1 -> System.out.println("1阶段");
                case 2 -> System.out.println("2阶段");
                case 3 -> {
                    System.out.println("终止阶段");
                    return true;
                }
            }
            return false;
        }
    }
}
