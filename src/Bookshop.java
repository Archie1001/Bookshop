import java.sql.*;

public class Bookshop implements SqlQueries {

//    private int listId;

    Connection connection = null;

    public void addNewBook(Book book) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthorFirstName());
            preparedStatement.setString(3, book.getAuthorLastName());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setInt(5, book.getPublicationDate());
            preparedStatement.setInt(6, book.getAvailableBooks());

            connection.prepareStatement(QUERY_ADD_NEW_BOOK_TO_SHOP);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDouble(2, book.getPrice());

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Adding new book: " + book.getTitle() + "\n" + numberOfRecords + " record(s) inserted\n");

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeBookById(int bookId) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_BOOK_BY_ID);
            preparedStatement.setInt(1, bookId);
            connection.prepareStatement(QUERY_REMOVE_BOOK_BY_ID_FROM_LIST);
            preparedStatement.setInt(1, bookId);

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Removing all books from the shop by id\n" + numberOfRecords + " record(s) removed\n");

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

            System.out.println("Show all available books in shop\n");

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
            System.out.println("\n");
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

            System.out.println("Show all books in Bookshop: \n");

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
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void setNumberOfAvailableBooksById(int bookId, int availableBooks) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SET_NUMBER_OF_AVAILABLE_BOOKS);
            preparedStatement.setInt(1, availableBooks);
            preparedStatement.setInt(2, bookId);

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Changing number of available books\n" + numberOfRecords + " record updated\n");

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
