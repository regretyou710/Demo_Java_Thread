package tw.com.thread;

public class Demo03 {

	public static void main(String[] args) {
		Account account = new Account(0);
		Customer c1 = new Customer(account);
		Customer c2 = new Customer(account);
		c1.setName("甲");
		c2.setName("乙");
		c1.start();
		c2.start();
	}

}

class Account {

	private double balance;

	public Account(double balance) {
		this.balance = balance;
	}

	public synchronized void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + "存款成功 餘額:" + balance);

		}

	}
}

class Customer extends Thread {
	private Account account;

	public Customer(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		System.out.println(getName() + "開始存款");
		for (int i = 0; i < 3; i++) {
			account.deposit(1000);
		}

	}
}