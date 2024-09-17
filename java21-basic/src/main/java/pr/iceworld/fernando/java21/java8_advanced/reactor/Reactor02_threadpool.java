package pr.iceworld.fernando.java21.java8_advanced.reactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Reactor02_ThreadPool {

    public static void main(String[] args) throws IOException {
        BasicModel02 basicModel = new BasicModel02();
        Thread thread = new Thread(basicModel);
        thread.start();
    }
}


class BasicModel02 implements Runnable {
    static int port = 3356;
    ExecutorService executorService = new ThreadPoolExecutor(
            5,
            5,
            30000,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            while (!Thread.interrupted())
                executorService.execute(new Handler(ss.accept()));
            //创建新线程来handle
            // or, single-threaded, or a thread pool
        } catch (IOException ex) { /* ... */ } finally {
            executorService.shutdown();
        }
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