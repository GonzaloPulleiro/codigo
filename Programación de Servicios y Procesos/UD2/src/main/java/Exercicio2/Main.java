package Exercicio2;

public class Main {
	
	private static int contador = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			Thread hilo = new Thread() {
				@Override
				public void run() {
				for (int j = 0; j<5000; j++) {
				contador ++;
			}}};
		hilo.start();
		}
		while (Thread.activeCount() > 1) {
	            Thread.yield();
	        }
		System.out.println(contador);
	}
}
