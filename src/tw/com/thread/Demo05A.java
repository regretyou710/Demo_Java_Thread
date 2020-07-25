package tw.com.thread;

public class Demo05A {
	public static void main(String[] args) {
		Number number1 = new Number();
		Number number2 = new Number();
		number1.setName("線程一");
		number2.setName("線程二");
		number1.start();
		number2.start();

	}
}

class Number extends Thread {
	private static int number = 1;

	@Override
	public void run() {

		while (true) {
			display();
		}
	}

	public static synchronized void display() {
		
		Number.class.notify();
			if (number <= 100) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " number=" + number);
				number++;

				try {
					Number.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		
	}
}
