package Exercicio5;

public class Matriz {
	    public static void main(String[] args) {
	        
	        int[][] matrizA = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};

	        int[][] matrizB = {{9, 8, 7},{6, 5, 4},{3, 2, 1}};
 
	        long inicio = System.currentTimeMillis();

	        int filasA = matrizA.length;
	        int columnasA = matrizA[0].length;
	        int columnasB = matrizB[0].length;

	        int[][] resultado = new int[filasA][columnasB];

	        for (int i = 0; i < filasA; i++) {
	            for (int j = 0; j < columnasB; j++) {
	                for (int k = 0; k < columnasA; k++) {
	                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
	                }
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
	}
