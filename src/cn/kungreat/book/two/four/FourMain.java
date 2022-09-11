package cn.kungreat.book.two.four;

public class FourMain {

    public static void main(String[] args) throws InterruptedException {

//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println("第三阶段,进入了对象的异常处理:"+e.getMessage());
//            }
//        });

//        FourGroup fourGroup = new FourGroup("第二阶段");
//        FourThread fourThread = new FourThread(fourGroup,"kungreat");

        FourThread fourThread = new FourThread();

        fourThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("第一阶段,进入了对象的异常处理:"+e.getMessage());
            }
        });
        fourThread.start();

        Thread.sleep(1000);
        System.out.println(fourThread.getState());
    }

}
