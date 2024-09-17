package pr.iceworld.fernando.java21.java8_advanced.reactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Reactor01 {

    public static void main(String[] args) throws IOException {
        BasicModel01 basicModel = new BasicModel01();
        Thread thread = new Thread(basicModel);
        thread.start();
    }
}


class BasicModel01 implements Runnable {
    static int port = 3356;

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            while (!Thread.interrupted())
                new Thread(new Handler(ss.accept())).start();
            //创建新线程来handle
            // or, single-threaded, or a thread pool
        } catch (IOException ex) { /* ... */ }
    }

    static class Handler implements Runnable {
        final Socket socket;

        Handler(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                System.out.println("New client connected " + socket.getRemoteSocketAddress());

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Read message from the client and echo it back
                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    if (clientMessage.equals("exit")) break;
                    System.out.println("Client says: " + clientMessage);
                    out.println(clientMessage); // Echo the received message back to the client
                }

            } catch (IOException ex) {
                /* ... */
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}