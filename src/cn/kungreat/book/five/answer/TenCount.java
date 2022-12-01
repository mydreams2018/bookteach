package cn.kungreat.book.five.answer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 *模拟从1累加到100,创建10个线程平分此任务,主线程等待其它执行线程任务完成后,统计计算结果并输出.
 * 此案例皆在演示执行线程之间如何协作,FutureRun对象的数据并没有多线程并发操作.
 */
public class TenCount {

    public static void main(String[] args) {
        FutureRun[] futureRuns = new FutureRun[10];
        for (int i = 0; i < futureRuns.length; i++) {
            FutureRun futureRun = new FutureRun(i * 10 + 1);
            futureRuns[i] = futureRun;
            new Thread(futureRun).start();
        }
        int numCount = 0;
        for (int i = 0; i < futureRuns.length; i++) {
            numCount = numCount + futureRuns[i].getCountNum();
        }
        System.out.println("总结果:"+numCount);
    }

    static final class FutureRun implements Runnable {

        private final ReentrantLock reentrantLock = new ReentrantLock();
        private final Condition condition = reentrantLock.newCondition();
        private final int startNum;
        private boolean isEnd = false;
        private int countNum;

        public FutureRun(int startNum) {
            this.startNum = startNum;
        }

        @Override
        public void run() {
            int endNum = startNum + 10;
            reentrantLock.lock();
            try {
                for (int i = startNum; i < endNum; i++) {
                    countNum = countNum + i;
                    Thread.sleep(1000);
                }
                System.out.println(Thread.currentThread().getName() + ":done");
            } catch (InterruptedException e) {
                countNum = 0;
                e.printStackTrace();
            } finally {
                isEnd = true;
                condition.signal();
                reentrantLock.unlock();
            }
        }

        public int getCountNum() {
            reentrantLock.lock();
            try {
                while (!isEnd) {
                    condition.await();
                }
                return countNum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
            return 0;
        }
    }
}
