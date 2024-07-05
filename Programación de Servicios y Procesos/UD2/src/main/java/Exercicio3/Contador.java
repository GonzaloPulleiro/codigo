package Exercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Contador {

    public static void main(String[] args) {

        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;

        for (String nombreArchivo : args) {
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
                continue;
            }

            System.out.println("Estadísticas para " + nombreArchivo + ":");
            System.out.println("  Líneas: " + lineas);
            System.out.println("  Palabras: " + palabras);
            System.out.println("  Caracteres: " + caracteres);
            System.out.println();

            totalLineas += lineas;
            totalPalabras += palabras;
            totalCaracteres += caracteres;
        }

        System.out.println("Total de estadísticas:");
        System.out.println("  Líneas: " + totalLineas);
        System.out.println("  Palabras: " + totalPalabras);
        System.out.println("  Caracteres: " + totalCaracteres);
    }

    private static int contarPalabras(String linea) {
        String[] palabras = linea.split("\\s+");
        return palabras.length;
    }
}
