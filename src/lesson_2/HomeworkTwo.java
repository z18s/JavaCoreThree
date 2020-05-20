package lesson_2;

import java.sql.*;

public class HomeworkTwo {

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {

        try {
            connect();

            ResultSet rs;
            int res;

            // CREATE

            res = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tbl " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER);");
            System.out.println("CREATE: " + res);
            System.out.println();

            // INSERT

            res = stmt.executeUpdate("INSERT INTO tbl (name, score) VALUES ('Tom', 30), ('Mia', 20), ('Bob', 10);");
            System.out.println("INSERT: " + res);
            System.out.println();

            // UPDATE

            res = stmt.executeUpdate("UPDATE tbl SET score = 20 WHERE name = 'Bob';");
            System.out.println("UPDATE: " + res);
            System.out.println();

            // SELECT

            rs = stmt.executeQuery("SELECT * FROM tbl;");
            System.out.println("SELECT:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("score"));
            }
            System.out.println();

            // DELETE

            res = stmt.executeUpdate("DELETE FROM tbl;");
            System.out.println("DELETE: " + res);
            System.out.println();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        disconnect();
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src\\lesson_2\\hw2.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}