package Exercicio4;

import java.util.Random;

class Carreira {
    private static final int META = 70;
    private static final int INICIO = 1;
    
    private int posicionTartaruga;
    private int posicionLebre;

    public Carreira() {
        this.posicionTartaruga = INICIO;
        this.posicionLebre = INICIO;
    }

    public void simularCarreira() throws InterruptedException {
        System.out.println("¡Comeza a carreira!");

        while (posicionTartaruga < META && posicionLebre < META) {
            Thread.sleep(1000); // Suspender durante un segundo

            movTartaruga("Tartaruga", "T", 50, 20, 30);
            movLebre("Lebre", "L", 20, 20, 10, 30, 20);

            imprimirCarreira();
        }

        determinarGanador();
    }

    private void movTartaruga(String animal, String representacion, int probAvanceRapido, int probEsvarar, int probAvanceLento) {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100) + 1; 

        if (numeroAleatorio <= probAvanceRapido) {
            avanzarAnimal(animal, representacion, 3);
        } else if (numeroAleatorio <= probAvanceRapido + probEsvarar) {
            retrocederAnimal(animal, representacion, 6);
        } else {
            avanzarAnimal(animal, representacion, 1);
        }
    }

    private void movLebre(String animal, String representacion, int probDormir, int probGranSalto, int probEsvaronGrande, int probPequenoSalto, int probEsvaronPequeno) {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100) + 1; 

        if (numeroAleatorio <= probDormir) {
            //Non fai nada
        } else if (numeroAleatorio <= probDormir + probGranSalto) {
            avanzarAnimal(animal, representacion, 9);
        } else if (numeroAleatorio <= probDormir + probGranSalto + probEsvaronGrande) {
            retrocederAnimal(animal, representacion, 12);
        } else if (numeroAleatorio <= probDormir + probGranSalto + probEsvaronGrande + probPequenoSalto) {
            avanzarAnimal(animal, representacion, 1);
        } else {
            retrocederAnimal(animal, representacion, 2);
        }
    }

    //movemento á dereita, de avance
    private void avanzarAnimal(String animal, String representacion, int pasos) {
        if (animal.equals("Tartaruga")) {
            posicionTartaruga = Math.min(posicionTartaruga + pasos, META);
        } else if (animal.equals("Lebre")) {
            posicionLebre = Math.min(posicionLebre + pasos, META);
        }
    }

    //movemento á esquerda, de retroceso
    private void retrocederAnimal(String animal, String representacion, int pasos) {
        if (animal.equals("Tartaruga")) {
            posicionTartaruga = Math.max(INICIO, posicionTartaruga - pasos);
        } else if (animal.equals("Lebre")) {
            posicionLebre = Math.max(INICIO, posicionLebre - pasos);
        }
    }

    private void imprimirCarreira() {
        for (int i = INICIO; i <= META; i++) {
            if (i == posicionTartaruga) {
                System.out.print("T");
            } else if (i == posicionLebre) {
                System.out.print("L");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private void determinarGanador() {
        if (posicionTartaruga >= META && posicionLebre >= META) {
            System.out.println("¡Empate!");
        } else if (posicionTartaruga >= META) {
            System.out.println("¡Gana a Tartaruga!");
        } else {
            System.out.println("¡Gana a Lebre!");
        }
    }
}
