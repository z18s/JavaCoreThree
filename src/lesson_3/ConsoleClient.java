package lesson_3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private Scanner scanner = new Scanner(System.in);
    private boolean status = true;

    Thread threadSend;

    public static void main(String[] args) {

        ConsoleClient client = new ConsoleClient();

        try {
            client.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Connection reset.");
        }
    }

    public void openConnection() throws IOException {
        String SERVER_ADDR = "localhost";
        int SERVER_PORT = 8189;

        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        System.out.println("Server connected.");

        outputStream = new ObjectOutputStream(socket.getOutputStream());

        threadSend = new Thread(this::sendObject);
        threadSend.start();
    }

    public void exit() {
        closeConnection();
        System.exit(0);
    }

    public void closeConnection() {
        scanner.close();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server disconnected.");
    }

    private void sendObject() {
        String message;
        while (status) {
            message = scanner.nextLine();
            if (!message.isBlank()) {
                if (message.equals("/end")) {
                    status = false;
                    exit();
                    break;
                }
                try {
                    if (message.equals("/send")) {
                        outputStream.writeObject(new Box("apple", 5));
                    } else {
                        outputStream.writeObject(message);
                    }
                } catch (IOException e) {
                    System.err.println("Message output error!");
                }
            }
        }
    }
}