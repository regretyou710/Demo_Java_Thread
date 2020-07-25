package tw.com.thread;

public class Demo04 {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Producer p1 = new Producer(clerk);
		Consumer c1 = new Consumer(clerk);
		p1.setName("生產者");
		c1.setName("消費者");
		p1.start();
		c1.start();
		
	}

}

class Clerk {
	private int productCount = 0;

	public synchronized void producerProduct() {
		if (productCount < 20) {			
			productCount++;
			System.out.println(Thread.currentThread().getName() + "開始生產 第" + productCount + "個商品");
			notify();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public synchronized void consumerProduct() {
		if (productCount > 0) {
			System.out.println(Thread.currentThread().getName() + "開始消費 第" + productCount + "個商品");
			productCount--;
			notify();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Producer extends Thread {
	private Clerk clerk;

	public Producer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		System.out.println("開始生產");
		while (true) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.producerProduct();
		}
	}
}

class Consumer extends Thread {
	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		System.out.println("開始消費");
		while (true) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.consumerProduct();
		}
	}
}