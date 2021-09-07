import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Book book1 = new Book("Tom Sawyer", "Mark", "Twain");
        book1.setGenre("Novel");

        Bookshop bookshop = new Bookshop();

//        bookshop.addNewBook(book1);

        bookshop.showAllBooksInShop();
    }
}
