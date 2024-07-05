package Exercicio5;

public class MatrizModificada {

	    private static int[][] matrizA = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};

	    private static int[][] matrizB = {{9, 8, 7},{6, 5, 4},{3, 2, 1}};

	    private static int[][] resultado;

	    public static void main(String[] args) {
	      
	        resultado = new int[matrizA.length][matrizB[0].length];

	        long inicio = System.currentTimeMillis();

	        int filasA = matrizA.length;
	        int columnasB = matrizB[0].length;

	        Thread[] threads = new Thread[4];

	        // Dividir la tarea entre los cuatro hilos
	        for (int i = 0; i < 4; i++) {
	            int inicioFila = i * filasA / 4;
	            int finFila = (i + 1) * filasA / 4;

	            threads[i] = new Thread(new MultiplicadorMatrices(inicioFila, finFila, columnasB));
	            threads[i].start();
	        }

	        for (Thread thread : threads) {
	            try {
	                thread.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        long fin = System.currentTimeMillis();
	        System.out.println("Matriz resultante:");
	        imprimirMatriz(resultado);
	        System.out.println("Tiempo empleado: " + (fin - inicio) + " ms");
	    }

	    private static void imprimirMatriz(int[][] matriz) {
	        for (int i = 0; i < matriz.length; i++) {
	            for (int j = 0; j < matriz[0].length; j++) {
	                System.out.print(matriz[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }

	    private static class MultiplicadorMatrices extends Thread {
	        private int inicioFila;
	        private int finFila;
	        private int columnasB;

	        public MultiplicadorMatrices(int inicioFila, int finFila, int columnasB) {
	            this.inicioFila = inicioFila;
	            this.finFila = finFila;
	            this.columnasB = columnasB;
	        }

	        @Override
	        public void run() {
	            for (int i = inicioFila; i < finFila; i++) {
	                for (int j = 0; j < columnasB; j++) {
	                    for (int k = 0; k < matrizB.length; k++) {
	                        resultado[i][j] += matrizA[i][k] * matrizB[k][j];
	                    }
	                }
	            }
	        }
	    }
	}
