package Exercicio7;

class BufferCircular {
    private int[] buffer;
    private int capacidad;
    private int count; // nº elementos en buffer
    private int nextIn; // siguiente producción
    private int nextOut; // siguiente consumición

    public BufferCircular(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new int[capacidad];
        this.count = 0;
        this.nextIn = 0;
        this.nextOut = 0;
    }

    public synchronized void poner(int dato) throws InterruptedException {
        while (count == capacidad) {
            System.out.println("Buffer lleno. El productor espera.");
            wait();
        }

        buffer[nextIn] = dato;
        nextIn = (nextIn + 1) % capacidad;
        count++;

        System.out.println("Productor produce: " + dato);
        notifyAll();
    }

    public synchronized int sacar() throws InterruptedException {
        while (count == 0) {
            System.out.println("Buffer vacío. El consumidor espera.");
            wait();
        }

        int dato = buffer[nextOut];
        nextOut = (nextOut + 1) % capacidad;
        count--;

        System.out.println("Consumidor consume: " + dato);
        notifyAll();

        return dato;
    }
}