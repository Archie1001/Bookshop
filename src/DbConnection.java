import java.sql.*;

public class DbConnection {

    private static final String jdbcUrl = "jdbc:sqlite:C:\\Users\\amaka\\IdeaProjects\\Bookshop\\Bookshop.db";

    private static Connection connection = null;

    public static Connection createDbConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }

    public static void executeQueryListOfBooks(String query) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            // printing out column order from List_of_books table
            int i = 0;
            while (i < resultSetMetaData.getColumnCount() - 1) {
                i++;
                System.out.print(resultSetMetaData.getColumnName(i) + " | ");
            }
            System.out.println(resultSetMetaData.getColumnName(i + 1));

            // printing out results
            while (resultSet.next()) {
                int listId = resultSet.getInt("List_id");
                int bookId = resultSet.getInt("Book_id");
                float price = resultSet.getFloat("Price");
                String title = resultSet.getString("Title");
                int availableBooks = resultSet.getInt("Available_books");

                System.out.println(listId + " | " + bookId + " | " + price + " | " + title + " | " + availableBooks);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }
}


