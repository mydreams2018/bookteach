package cn.kungreat.book.two.two;

public class TwoMain {

    public static void main(String[] args) {
//        new Thread(Thread.currentThread().getThreadGroup(),()->{
//            TwoMain.recursion(100000);
//            System.out.println(Thread.currentThread().getName());
//        },"kkkk",99999999).start();

        TwoMain.recursion(100000);
    }


    public static void recursion(int nums){
        if (nums != 0) {
            recursion(--nums);
        }
    }
}
