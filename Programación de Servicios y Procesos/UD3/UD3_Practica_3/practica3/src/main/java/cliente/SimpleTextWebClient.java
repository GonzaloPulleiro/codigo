package cliente;

public class SimpleTextWebClient {

	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			SimpleTextWebClientTask clientTask = new SimpleTextWebClientTask(i);
			Thread cliente = new Thread(clientTask);
			cliente.start();
			
		}

	}

}
