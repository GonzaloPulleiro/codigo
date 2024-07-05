package Exercicio7;

public class Main {
    public static void main(String[] args) {
        BufferCircular buffer = new BufferCircular(3);

        Thread productorThread = new Thread(new Productor(buffer));
        Thread consumidorThread = new Thread(new Consumidor(buffer));

        productorThread.start();
        consumidorThread.start();
    }
}
