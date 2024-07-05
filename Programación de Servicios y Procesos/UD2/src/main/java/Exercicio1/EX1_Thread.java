package Exercicio1;

public class EX1_Thread extends Thread {

	
	public EX1_Thread() {
		
	}
	public void run() {
		for (int i = 1; i <= 6; i++) {
			System.out.println("Hola mundo!!"+ getName()+i);
		try {
			Thread.sleep(1000*i);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		} //System.out.println("FIN THREAD");
				
	}
}
