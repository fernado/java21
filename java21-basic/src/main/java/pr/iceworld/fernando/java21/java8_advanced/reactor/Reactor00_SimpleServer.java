package pr.iceworld.fernando.java21.java8_advanced.reactor;

import java.io.*;
import java.net.*;
 
public class Reactor00_SimpleServer {


    static int port = 3356;
    static ServerSocket serverSocket = null;


    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                // Wait for a connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected " + clientSocket.getRemoteSocketAddress());
 
                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
 
                // Read message from the client and echo it back
                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    if (clientMessage.equals("exit")) break;
                    System.out.println("Client says: " + clientMessage);
                    out.println(clientMessage); // Echo the received message back to the client
                }
 
                // Close the connection with the client
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the server socket
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}