package tw.com.thread;

public class Demo05B {
	public static void main(String[] args) {
		Number1 number1 = new Number1();
		Number1 number2 = new Number1();
		number1.setName("線程一");
		number2.setName("線程二");
		number1.start();
		number2.start();

	}
}

class Number1 extends Thread {
	private static int number = 1;

	@Override
	public void run() {

		while (true) {
			synchronized (Number1.class) {
				Number1.class.notify();
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
						Number1.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					break;
				}
			}

		}
	}

}
