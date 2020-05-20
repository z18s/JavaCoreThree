package server_work;

import auth.AuthService;
import auth.BaseAuthService;
import client_work.ClientHandler;
import logic.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int port;
    private final List<ClientHandler> clients;
    private final AuthService authService;

    private static Connection connection;
    private static Statement stmt;

    public MyServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.authService = new BaseAuthService();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running.");

            try {
                connectDatabase();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Failed to database connection!");
                e.printStackTrace();
            }

            authService.start();

            //noinspection InfiniteLoopStatement
            while (true) {
                System.out.println("Waiting for client connection...");

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has been connected.");

                ClientHandler handler = new ClientHandler(clientSocket, this);
                try {
                    handler.handle();
                } catch (IOException e) {
                    System.err.println("Failed to handle client connection!");
                    clientSocket.close();
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
            disconnectDatabase();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNicknameBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(Command command) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(command);
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) throws IOException {
        clients.add(clientHandler);
        List<String> users = getAllUsernames();
        broadcastMessage(Command.updateUsersListCommand(users));
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        List<String> users = getAllUsernames();
        broadcastMessage(Command.updateUsersListCommand(users));
    }

    private List<String> getAllUsernames() {
        List<String> result = new ArrayList<>();
        for (ClientHandler client : clients) {
            result.add(client.getNickname());
        }
        return result;
    }

    public synchronized void sendPrivateMessage(String receiver, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(receiver)) {
                client.sendMessage(command);
                return;
            }
        }
    }

    public static void connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:NetworkServer/src/server_work/events.db");
        stmt = connection.createStatement();

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS connections " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, connect_type TEXT, connect_at DATETIME);");
    }

    public static void disconnectDatabase() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logAuthDatabase(String name) {
        try {
            stmt.executeUpdate(String.format(
                    "INSERT INTO connections (name, connect_type, connect_at) " +
                            "VALUES ('%s', 'auth_success', CURRENT_TIMESTAMP);", name
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logAuthFailedDatabase(String name) throws SQLException {
        stmt.executeUpdate(String.format(
                "INSERT INTO connections (name, connect_type, connect_at) " +
                        "VALUES ('%s', 'auth_failed', CURRENT_TIMESTAMP);", name
        ));
    }

    public static void logQuitDatabase(String name) {
        try {
            if (name == null) {
                logAuthFailedDatabase("Unknown");
            } else {
                stmt.executeUpdate(String.format(
                        "INSERT INTO connections (name, connect_type, connect_at) " +
                                "VALUES ('%s', 'quit', CURRENT_TIMESTAMP);", name
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}