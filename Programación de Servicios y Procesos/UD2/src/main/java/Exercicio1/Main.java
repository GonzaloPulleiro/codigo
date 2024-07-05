package Exercicio1;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Thread hilo1 = new EX1_Thread();
		hilo1.start();
		
		
		Thread fio1 = new Thread(new EX1_Runnable());
		fio1.start();
		
		
		
		
	}

}
