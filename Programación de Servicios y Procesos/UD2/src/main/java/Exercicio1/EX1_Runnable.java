package Exercicio1;

public class EX1_Runnable implements Runnable {

    public void run() {
        for (int i = 1; i <= 6; i++) {
            System.out.println("Hola mundo!! Hilo " + i);
            try {
                Thread.sleep(1000 * i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } //System.out.println("FIN RUNNABLE");
    }
}
