import java.sql.*;

public class Bookshop implements SqlQueries {

    Connection connection = null;

    public void addNewBook(Book book) throws SQLException {

        Utils.executeQueryAddNewBook(book.getTitle(), book.getAuthorFirstName(), book.getAuthorLastName(), book.getGenre(), book.getPublicationDate(), book.getAvailableBooks(), book.getPrice());
    }

    public void removeBookById(int bookId) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_BOOK_BY_ID);
            preparedStatement.setInt(1, bookId);

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Removing book from \"Books\" table with id: " + bookId + "\n" + numberOfRecords + " record(s) removed\n");

            preparedStatement = connection.prepareStatement(QUERY_REMOVE_BOOK_BY_ID_FROM_LIST);
            preparedStatement.setInt(1, bookId);

            numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Removing all books from the shop by id\n" + numberOfRecords + " record(s) removed\n");

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    public void showAllBooksInShop() throws SQLException {

        System.out.println("Show all books in Bookshop: \n");
        Utils.executeQueryShowListOfBooks(QUERY_GET_ALL_BOOKS_IN_SHOP);
        System.out.println();
    }

    public void showAllAvailableBooksInShop() throws SQLException {

        System.out.println("Show all available books in shop\n");
        Utils.executeQueryShowListOfBooks(QUERY_GET_ALL_AVAILABLE_BOOKS_IN_SHOP);
        System.out.println();
    }

    public void setNumberOfAvailableBooksById(int bookId, int numberOfAvailableBooks) throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SET_NUMBER_OF_AVAILABLE_BOOKS_BY_ID);
            preparedStatement.setInt(1, numberOfAvailableBooks);
            preparedStatement.setInt(2, bookId);

            int numberOfRecords = preparedStatement.executeUpdate();
            System.out.println("Changing number of available books with id " + bookId);
            System.out.println(numberOfRecords + " record(s) updated\n");

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    public void sellBookById(int bookId, int numberOfBooks) throws SQLException {

        int availableBooks = Utils.getAvailableBooksById(bookId);

        if (availableBooks - numberOfBooks >= 0) {
            setNumberOfAvailableBooksById(bookId, availableBooks - numberOfBooks);
            System.out.println("Sold " + numberOfBooks + " books with id " + bookId);
            System.out.println("Number of available books: " + (availableBooks - numberOfBooks) + "\n");
        } else {
            System.out.println("There are not enough books to sell!\nNumber of available books: " + availableBooks + "\n");
        }
    }

    public void refundBookById(int bookId) throws SQLException{

        int availableBooks = Utils.getAvailableBooksById(bookId);

        setNumberOfAvailableBooksById(bookId, (availableBooks + 1));
        System.out.println("Refunded a book with id " + bookId + "\nNumber of available books: " + (availableBooks + 1));
    }
}