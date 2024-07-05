package Exercicio2;

public class Main_Sinc {

	private static int contador = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Thread hilo = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 5000; j++) {
                    	synchronized (lock) {
                            contador++;
                  }}}};
            hilo.start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(contador);
    } 
}

