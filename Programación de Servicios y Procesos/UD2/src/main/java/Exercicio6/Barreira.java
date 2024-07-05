package Exercicio6;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
	
public class Barreira {

		    private static final int NUM_HILOS = 5;

		    public static void main(String[] args) throws InterruptedException {
		        
		        CyclicBarrier barreira = new CyclicBarrier(NUM_HILOS, () -> {
		            
		            System.out.println("Todos los hilos han llegado a la barrera. Continuando la ejecución.");
		        });

		        
		        for (int i = 0; i < NUM_HILOS; i++) {
		            Thread hilo = new Thread(new TareaHilo(barreira));
		            hilo.start();
		            
		        }
		    }

		    static class TareaHilo implements Runnable {
		        private final CyclicBarrier barreira;

		        public TareaHilo(CyclicBarrier barreira) {
		            this.barreira = barreira;
		        }

		        @Override
		        public void run() {
		            try {
		                System.out.println("Hilo " + Thread.currentThread().getName() + " "
		                		+ "realizando tarea antes de llegar a la barrera.");

		                barreira.await();

		                System.out.println("Hilo " + Thread.currentThread().getName() + " "
		                		+ "realizando tarea después de pasar la barrera.");
		            } catch (InterruptedException | BrokenBarrierException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
