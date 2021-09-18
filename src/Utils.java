import java.sql.*;
import java.util.ArrayList;

public class Utils implements SqlQueries{

    static Connection connection = null;

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

    public static int getAvailableBooksById(int bookId) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_NUMBER_OF_AVAILABLE_BOOKS_BY_ID + bookId);

            return resultSet.getInt("Available_books");

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
            return 0;
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    public static ArrayList<String> getUsedTitles() throws SQLException {
// TODO: ASK TARAS
        try {
            connection = DbConnection.createDbConnection();

            ArrayList<String> usedTitles = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_USED_TITLES);

            while (resultSet.next()) {
                usedTitles.add(resultSet.getString("Title"));
            }
            return usedTitles;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
            } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
