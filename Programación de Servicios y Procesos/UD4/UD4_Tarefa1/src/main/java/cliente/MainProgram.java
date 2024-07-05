package cliente;

public class MainProgram {

	public static void main(String[] args) {

		 Thread[] fioCliente = new Thread[10];
		 for (int i = 0; i < 10; i++) {
	            // Crear un novo fio para cada cliente
			 fioCliente[i] = new Thread(new SimpleTextWebClientTask(i + 1));

	            // Iniciar  fio
			 fioCliente[i].start();
	        }

	        // Esperar a que todos os clientes finalicen 
	        try {
	            for (Thread clienteThread : fioCliente) {
	                clienteThread.join();
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
