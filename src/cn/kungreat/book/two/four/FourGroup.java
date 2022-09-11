package cn.kungreat.book.two.four;

public class FourGroup extends ThreadGroup{
    public FourGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("第二阶段,进入了对象的异常处理:"+e.getMessage());
    }
}
