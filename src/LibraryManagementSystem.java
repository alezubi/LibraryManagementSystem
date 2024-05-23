
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ale.zubi99";

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Create a scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Library Management System");
                System.out.println("1. Add a book");
                System.out.println("2. Update a book");
                System.out.println("3. Remove a book");
                System.out.println("4. Show all books");
                System.out.println("5. Exit");

                System.out.print("Enter your choice (1-5): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addBook(connection, scanner);
                        break;
                    case 2:
                        updateBook(connection, scanner);
                        break;
                    case 3:
                        removeBook(connection, scanner);
                        break;
                    case 4:
                        showAllBooks(connection);
                        break;
                    case 5:
                        System.out.println("Exiting the Library Management System...");
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL connection error.");
            e.printStackTrace();
        }
    }

    private static void addBook(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter price: ");
        String price = scanner.nextLine();
        System.out.print("Enter date: ");
        String date = scanner.nextLine();
        System.out.print("Enter volume: ");
        String volume = scanner.nextLine();

        Book book = new Book(0, author, title, price, date, volume);
        addBookToDatabase(connection, book);
        System.out.println("Book added successfully.");
    }

    private static void addBookToDatabase(Connection connection, Book book) throws SQLException {
        String sql = "INSERT INTO books (author, title, price, date, volume) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getPrice());
        statement.setString(4, book.getDate());
        statement.setString(5, book.getVolume());
        statement.executeUpdate();
    }

    private static void updateBook(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the book to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter new author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new price: ");
        String price = scanner.nextLine();
        System.out.print("Enter new date: ");
        String date = scanner.nextLine();
        System.out.print("Enter new volume: ");
        String volume = scanner.nextLine();

        String sql = "UPDATE books SET author = ?, title = ?, price = ?, date = ?, volume = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, author);
        statement.setString(2, title);
        statement.setString(3, price);
        statement.setString(4, date);
        statement.setString(5, volume);
        statement.setInt(6, id);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("No book found with the given ID.");
        }
    }

    private static void showAllBooks(Connection connection) throws SQLException {
        String sql = "SELECT * FROM books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("ID\tAuthor\tTitle\tPrice\tDate\tVolume");
        System.out.println("---\t------\t-----\t-----\t----\t------");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String author = resultSet.getString("author");
            String title = resultSet.getString("title");
            String price = resultSet.getString("price");
            String date = resultSet.getString("date");
            String volume = resultSet.getString("volume");
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n", id, author, title, price, date, volume);
        }
    }

    private static void removeBook(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the book to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String sql = "DELETE FROM books WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with the given ID.");
        }
    }


}