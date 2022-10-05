package cn.kungreat.book.three.five;

/*
* 重排序   volatile
* A线程先执行完 x==0 y==1
* B线程先执行完 x==1 y==0
* 一起执行完 x==1 y==1
*/
public class PossibleReordering {
	static volatile int x, y = 0;
	static volatile int a, b = 0;

	public static void main(String[] args) throws Exception {
		for (int num = 0;;) {
			num++;
			x=0;y=0;a=0;b=0;//重置数据
			Thread threadA = new Thread(new Runnable() {
				@Override
				public void run() {
					a = 1;
					x = b;
					/* 重排序后
					* x = b;
					* a = 1;
					*/
				}
			},"A");
			Thread threadB = new Thread(new Runnable() {
				@Override
				public void run() {
					b = 1;
					y = a;
				}
			},"B");
			threadA.start();//启动线程
			threadB.start();//启动线程
			threadA.join();
			threadB.join();
			if(x==0 && y==0) {
				System.out.println(num);
				return;
			}
		}
	}
}
