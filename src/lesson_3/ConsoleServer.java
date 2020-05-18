package lesson_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsoleServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream inputStream;
    private boolean status = true;

    Thread threadReceive;

    public static void main(String[] args) {

        ConsoleServer server = new ConsoleServer();

        try {
            server.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Connection reset.");
        }
    }

    public void openConnection() throws IOException {
        int SERVER_PORT = 8189;

        serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server started.");

        socket = serverSocket.accept();
        System.out.println("Client connected.");

        inputStream = new ObjectInputStream(socket.getInputStream());

        threadReceive = new Thread(this::receiveObject);
        threadReceive.start();
    }

    public void exit() {
        closeConnection();
        System.out.println("Server closed.");
        System.exit(0);
    }

    public void closeConnection() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client disconnected.");
    }

    private void receiveObject() {
        while (status) {
            try {
                Object clientAnswer = inputStream.readObject();
                if (clientAnswer.equals("/end")) {
                    status = false;
                    exit();
                    break;
                } else {
                    System.out.println("Client: " + clientAnswer.toString());
                    if (clientAnswer instanceof Box)
                    ((Box) clientAnswer).info();
                }
            } catch (EOFException e) {
                System.out.println("Connection closed by client.");
                status = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}