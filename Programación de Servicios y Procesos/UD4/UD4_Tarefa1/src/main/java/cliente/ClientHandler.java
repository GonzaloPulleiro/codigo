package cliente;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{

	private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
	@Override
    public void run() {
        try {
            // comunicación entrada/saida co cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // solicitude cliente
            String solicitude = reader.readLine();
            System.out.println("Solicitude recibida: " + solicitude);

            // Comprobar que sea unha solicitude GET
            if (solicitude != null && solicitude.startsWith("GET")) {
                
            	// Envia resposta ao cliente
                writer.println("HTTP/1.1 200 OK");
                writer.println();
                writer.println("¡Ola! Este é un servidor web simulado en Java.");

                System.out.println("Resposta enviada ó cliente.");
            }
            
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
