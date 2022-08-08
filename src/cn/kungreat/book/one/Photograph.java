package cn.kungreat.book.one;
/*
* 用来做代码片段 截图
*/
public class Photograph {

    public synchronized void start() {
        //Thread类start()方法源代码删减版只保留核心的内容
        boolean started = false;
        try {
            start0();
            started = true;
        } finally {

        }
    }
    private native void start0();
}
