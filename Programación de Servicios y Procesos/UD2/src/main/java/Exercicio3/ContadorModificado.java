package Exercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
	
public class ContadorModificado {

	private static int totalLineas = 0;
	    private static int totalPalabras = 0;
	    private static int totalCaracteres = 0;

	    public static void main(String[] args) {
	       
	        long inicioSecuencial = System.currentTimeMillis();
	        procesarArchivosSecuencialmente(args);
	        long finSecuencial = System.currentTimeMillis();
	        System.out.println("Tiempo empleado en la versión secuencial: " + (finSecuencial - inicioSecuencial) + " ms");

	        totalLineas = 0;
	        totalPalabras = 0;
	        totalCaracteres = 0;

	        long inicioParalelo = System.currentTimeMillis();
	        procesarArchivosParalelamente(args);
	        long finParalelo = System.currentTimeMillis();
	        System.out.println("Tiempo empleado en la versión paralela: " + (finParalelo - inicioParalelo) + " ms");
	    }

	    private static void procesarArchivosSecuencialmente(String[] archivos) {
	        for (String nombreArchivo : archivos) {
	            procesarArchivo(nombreArchivo);
	        }

	        imprimirTotales("Secuencial");
	    }

	    private static void procesarArchivosParalelamente(String[] archivos) {
	        Thread[] threads = new Thread[archivos.length];

	        for (int i = 0; i < archivos.length; i++) {
	            String nombreArchivo = archivos[i];
	            threads[i] = new Thread(() -> procesarArchivo(nombreArchivo));
	            threads[i].start();
	        }

	        for (Thread thread : threads) {
	            try {
	                thread.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        imprimirTotales("Paralelo");
	    }

	    private static void procesarArchivo(String nombreArchivo) {
	        int lineas = 0;
	        int palabras = 0;
	        int caracteres = 0;

	        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                lineas++;
	                palabras += contarPalabras(linea);
	                caracteres += linea.length();
	            }
	        } catch (IOException e) {
	            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
	        }

	        synchronized (ContadorModificado.class) {
	            totalLineas += lineas;
	            totalPalabras += palabras;
	            totalCaracteres += caracteres;
	        }
	    }

	    private static int contarPalabras(String linea) {
	        String[] palabras = linea.split("\\s+");
	        return palabras.length;
	    }

	    private static void imprimirTotales(String tipo) {
	        System.out.println("Total de estadísticas (" + tipo + "):");
	        System.out.println("  Líneas: " + totalLineas);
	        System.out.println("  Palabras: " + totalPalabras);
	        System.out.println("  Caracteres: " + totalCaracteres);
	    }
	}

