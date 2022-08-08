package cn.kungreat.book.one.four;

public class FourMain {

    public static void main(String[] args) {
//         单个线程执行 就是我们的主线程 main
        System.out.println("start-"+System.currentTimeMillis());
        FourThread p1 = new FourThread();
        p1.run();

        FourRunable p2 = new FourRunable();
        new Thread(p2).run();
        System.out.println("end-"+System.currentTimeMillis());

        // 这里有 main 主线程  还有另外二个线程启动执行
   /*     System.out.println("start-"+System.currentTimeMillis());
        FourThread p1 = new FourThread();
        p1.start();//启动新的线程

        FourRunable p2 = new FourRunable();
        new Thread(p2).start();//启动新的线程
        System.out.println("end-"+System.currentTimeMillis());*/
    }
}
