package tw.com.thread;

public class Demo06 {
	public static void main(String[] args) {
		Number2 number = new Number2();
		Thread t1 = new Thread(number);
		Thread t2 = new Thread(number);
		t1.setName("線程一");
		t2.setName("線程二");
		t1.start();
		t2.start();
	}
}

class Number2 implements Runnable {
	private int number = 1;	

	@Override
	public void run() {

		while (true) {
			synchronized (this) {
				notify();
				if (number <= 100) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " number=" + number);
					number++;

					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					break;
				}
			}

		}
	}
}
