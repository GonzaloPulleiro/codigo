package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleTextWebClientTask implements Runnable{
	
	private int clienteId;
	
	public SimpleTextWebClientTask(int clienteId) {
		this.clienteId = clienteId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try(Socket socket = new Socket("localhost", 8080);
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))
		)	{
			//Enviar solicitud al servidor
			String request = "GET / HTTP/1.1\r\nHost: localhost:8080\r\n\r\n";
			os.write(request.getBytes());
			
			//Respuesta del servidor
			String linea;
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
