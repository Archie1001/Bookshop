import java.sql.*;
import java.util.ArrayList;

public class Utils implements SqlQueries {

    static Connection connection = null;

    private static ArrayList<String> usedTitles = new ArrayList<>();

    public static void executeQueryShowListOfBooks(String query) throws SQLException {

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
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void executeQueryAddNewBook(String title, String authorFirstName, String authorLastName, String genre, int publicationDate, int availableBooks, double price) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_BOOK);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, authorFirstName);
            preparedStatement.setString(3, authorLastName);
            preparedStatement.setString(4, genre);
            preparedStatement.setInt(5, publicationDate);
            preparedStatement.setInt(6, availableBooks);

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Adding new book: " + title + "\n" + numberOfRecords + " record(s) inserted\n");

            preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_BOOK_TO_SHOP);
            preparedStatement.setString(1, title);
            preparedStatement.setDouble(2, price);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    public static void getUsedTitles(ArrayList<String> usedTitles) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_USED_TITLES);

            while (resultSet.next()) {
                usedTitles.add(resultSet.getString("Title"));
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     Returns true, if title already exists in usedTitles list
     */
    public static boolean checkUsedTitles(String title) throws SQLException {

        getUsedTitles(usedTitles);
        for (String usedTitle : usedTitles) {
            if (title.equals(usedTitle)) {
                return true;
            }
        } return false;
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
            if (connection != null) {
                connection.close();
            }
        }
    }
}
