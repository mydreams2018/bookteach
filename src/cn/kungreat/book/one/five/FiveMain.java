package cn.kungreat.book.one.five;

public class FiveMain {

    public static void main(String[] args) {
        FiveThread fiveThread = new FiveThread("通过构造方法-名称");
        fiveThread.setName("通过方法设置-名称");
        fiveThread.start();
    }
}
