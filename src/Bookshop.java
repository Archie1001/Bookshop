import java.sql.*;

public class Bookshop {

    private static final String QUERY_FOR_GET_ALL_BOOKS = "SELECT * FROM books";
    private static final String QUERY_ADD_NEW_BOOK = "INSERT INTO BOOKS VALUES ()";

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

    public void removeBookByID() {
    }

    public void showAllAvailableBooksByID() {
    }

    public void showAllBooksInShop() throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_FOR_GET_ALL_BOOKS);

            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                String author = resultSet.getString("Author");
                String genre = resultSet.getString("Genre");
                int publicationDate = resultSet.getInt("PublicationDate");

                System.out.println(title + " | " + author + " | " + genre + " | " + publicationDate);
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

    public void setNumberOfAvailableBooksByID() {
    }
}
