package cn.kungreat.book.two.three;


import java.util.Arrays;
import java.util.Map;

public class ThreeMain {

    public static void main(String[] args) throws InterruptedException {

        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        System.out.println(allStackTraces);

        Thread.onSpinWait();
    }
}
