package tw.com.thread;

public class Demo01 {

	public static void main(String[] args) {
		TicketWindow tWindow = new TicketWindow();
		Thread t1 = new Thread(tWindow);
		Thread t2 = new Thread(tWindow);
		Thread t3 = new Thread(tWindow);
		t1.setName("窗口一");
		t2.setName("窗口二");
		t3.setName("窗口三");
		t1.start();
		t2.start();
		t3.start();
	}
}

class TicketWindow implements Runnable {
	int ticket = 100;
	Object obj = new Object();
	@Override
	public void run() {
		while (true) {
			//synchronized (obj) {
			synchronized (this) {
				if (ticket > 0) {
					System.out.println(Thread.currentThread().getName() + " 餘票:餘票:" + ticket);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					ticket--;
				} else {
					break;
				}

			}
		}
	}
}