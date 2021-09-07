import org.sqlite.SQLiteException;

import java.sql.*;

public class Bookshop {

    private static final String QUERY_FOR_GET_ALL_BOOKS = "SELECT * FROM books";
    private static final String QUERY_ADD_NEW_BOOK = "INSERT INTO BOOKS (\"title\", \"author_first_name\", \"author_last_name\") " + "VALUES (?,?,?)";

    Connection connection = null;

    public void addNewBook(Book book) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_ADD_NEW_BOOK);

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeBookById() {
    }

    public void showAllAvailableBooksInShop() {
    }

    public void showAllBooksInShop() throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_FOR_GET_ALL_BOOKS);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int i = 0;
            while (i < resultSetMetaData.getColumnCount()) {
                i++;
                System.out.print(resultSetMetaData.getColumnName(i) + " | ");
            }
            System.out.print("\n");

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String authorFirstName = resultSet.getString("author_first_name");
                String authorLastName = resultSet.getString("author_last_name");
                String genre = resultSet.getString("genre");
                int publicationDate = resultSet.getInt("publication_date");

                System.out.println(title + " | " + authorFirstName + " | " + authorLastName + " | " + genre + " | " + publicationDate);
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

    public void setNumberOfAvailableBooksById() {
    }
}
