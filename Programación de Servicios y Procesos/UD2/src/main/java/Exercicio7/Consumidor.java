package Exercicio7;

import java.util.Random;

class Consumidor extends Thread {
    private BufferCircular buffer;
    private Random random = new Random();

    public Consumidor(BufferCircular buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 30; i++) {
            try {
                int dato = buffer.sacar();
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
