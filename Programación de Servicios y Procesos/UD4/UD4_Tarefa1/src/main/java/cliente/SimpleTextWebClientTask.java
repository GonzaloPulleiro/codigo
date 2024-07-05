package cliente;

import java.net.*;
import java.io.*;

public class SimpleTextWebClientTask implements Runnable{
	static String HOST = "localhost";
	static int PUERTO = 8080;
	Socket socket;
	private int clientId;

    public SimpleTextWebClientTask(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try {
            //Conectamos ao servidor
        	socket = new Socket(HOST, PUERTO);

            //Comunicación entrada saída co servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            //Solicitude HTTP GET ao servidor
            writer.println("GET / HTTP/1.1");
            writer.println("Host: localhost:8080");
            writer.println();
            
            System.out.println("Cliente " + clientId + " - Enviada solicitude HTTP GET ao servidor.");
 
            StringBuilder resposta = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
            	resposta.append(line).append("\n");
            }

            resposta.toString();
            System.out.println("Cliente " + clientId + " - Resposta do servidor:\n" + resposta);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
