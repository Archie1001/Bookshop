import java.sql.*;

public class DbConnection {

    private static final String jdbcUrl = "jdbc:sqlite:C:\\Users\\mupet\\IdeaProjects\\Bookshop\\Bookshop.db";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books";

    public static void openDbConnection() throws SQLException {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(SELECT_ALL_BOOKS);

            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                String author = resultSet.getString("Author");

                System.out.println(title + " | " + author);
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            closeDbConnectio();
        }
    }

    public static void closeDbConnectio() {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error closing the result set");
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing the statement");
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the connection");
            }
        }
    }
}