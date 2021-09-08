import java.sql.*;

public class Bookshop {

    private static final String QUERY_ADD_NEW_BOOK = "INSERT INTO BOOKS (\"title\", \"author_first_name\", \"author_last_name\") " + "VALUES (?,?,?)";
    private static final String QUERY_ADD_NEW_BOOK_TO_SHOP = "INSERT INTO LIST OF BOOKS (\"Book_id\", \"Price\", \"Is_available\", \"Title\") " + "VALUES (?,?,?,?)";
    private static final String QUERY_GET_ALL_BOOKS_IN_SHOP = "SELECT LIST_OF_BOOKS.*, BOOKS.Title FROM LIST_OF_BOOKS JOIN BOOKS ON LIST_OF_BOOKS.Book_id = BOOKS.Book_id";
    private static final String QUERY_REMOVE_BOOK_BY_ID = "DELETE FROM LIST_OF_BOOKS WHERE \"Book_id\" = ?";
    private static final String QUERY_GET_ALL_AVAILABLE_BOOKS = "SELECT LIST_OF_BOOKS.*, BOOKS.Title FROM LIST_OF_BOOKS JOIN BOOKS ON LIST_OF_BOOKS.Book_id = BOOKS.Book_id WHERE \"Is_available\" = 1";

    private int price;
    private int listId;

    private boolean isAvailable;

    Connection connection = null;

    public void addNewBook(Book book) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthorFirstName());
            preparedStatement.setString(3, book.getAuthorLastName());

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println(numberOfRecords + " record inserted");

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

//    public void addNewBookToShop(int bookId, float price, boolean isAvailable, String title) throws SQLException{
//
//        try {
//            connection = DbConnection.createDbConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_BOOK);
//            preparedStatement.setString(1, book.getTitle());
//            preparedStatement.setString(2, book.getAuthorFirstName());
//            preparedStatement.setString(3, book.getAuthorLastName());
//
//            int numberOfRecords = preparedStatement.executeUpdate();
//            System.out.println(numberOfRecords + " record inserted");
//
//        } catch (SQLException e) {
//            System.out.println("Error connecting to DB");
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }

    public void removeBookById(int bookId) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_BOOK_BY_ID);
            preparedStatement.setInt(1, bookId);

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println(numberOfRecords + " record removed");

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void showAllAvailableBooksInShop() throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_AVAILABLE_BOOKS);

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
                int isAvailable = resultSet.getInt("Is_available");
                String title = resultSet.getString("Title");

                System.out.println(listId + " | " + bookId + " | " + price + " | " + isAvailable + " | " + title);
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

    public void showAllBooksInShop() throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_BOOKS_IN_SHOP);

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
                int isAvailable = resultSet.getInt("Is_available");
                String title = resultSet.getString("Title");

                System.out.println(listId + " | " + bookId + " | " + price + " | " + isAvailable + " | " + title);
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
