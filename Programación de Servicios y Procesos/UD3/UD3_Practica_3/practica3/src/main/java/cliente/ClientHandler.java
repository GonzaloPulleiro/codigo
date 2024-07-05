package cliente;

import java.io.*;

import java.net.*;

public class ClientHandler implements Runnable {

	private Socket clienteSocket;

	public ClientHandler(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}


	@Override
	public void run() {
		try (
			BufferedReader input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
			OutputStream os = clienteSocket.getOutputStream()
			)
		{
			// Recibimos solicitude cliente
			StringBuilder recibido = new StringBuilder();
			String linea;
			while((linea = input.readLine()) != null && !linea.isEmpty()) {
				recibido.append(linea).append("\r\n");
			}
			
			
			// Simulamos acceso ao contido do servidor
			ProcessBuilder pb = new ProcessBuilder("echo", "¡Ola! Esta é unha tarefa de PSP.");
			Process process = pb.start();
			process.waitFor();
			

			//Respondemos al cliente
			String resposta = "HTTP/1.1 200 OK\r\n\r\n¡Ola! Este é un servidor web simulado en Java.\r\n";
			os.write(resposta.getBytes());
			
			//Cerramos conexion
			clienteSocket.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
