package cn.kungreat.book.three.answer;

/*
*一生产 一消费
*/
public class ConsumeMain {
    public static void main(String[] args) {
        ConsumeRunnable consumeRunnable = new ConsumeRunnable();
        new Thread(consumeRunnable,"A").start();
        consumeRunnable.consume();
    }

    static class ConsumeRunnable implements Runnable{
        private Boolean consume = false;//生产、消费状态标识
        private Object data=null;//存放消费的数据

        @Override
        public void run() {
            for (int x=0;x<10;x++){
                synchronized (this){
                    if(consume){
                        try {
                            this.wait();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    data = x;
                    consume=true;
                    this.notify();//有没有线程wait都可以调用
                    System.out.println(Thread.currentThread().getName()+":生产了"+x);
                }
            }
        }

        public void consume(){
            for(int x=0;x<10;x++){
                synchronized (this){
                    if(!consume){
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+":消费了"+data);
                    data=null;
                    consume=false;
                    this.notify();//有没有线程wait都可以调用
                }
            }
        }
    }
}
