import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            Book book1 = new Book("Harry Potter", "J.K.", "Rowling", "Fantasy", 2001, 5, 7.50);
//            book1.setGenre("Novel");

            Bookshop bookshop = new Bookshop();

//            bookshop.addNewBook(book1);

            bookshop.showAllBooksInShop();
//            bookshop.showAllAvailableBooksInShop();

//            RandomBook.getRandomBook();
//
            bookshop.sellBookById(5, 1);
            bookshop.showAllBooksInShop();

            bookshop.refundBookById(5);
            bookshop.showAllBooksInShop();

//            bookshop.setNumberOfAvailableBooksById(1, 6);
//
//            bookshop.showAllAvailableBooksInShop();
//
//            bookshop.removeBookById(11);
//
//            book1.showAllBooks();
//
//            bookshop.showAllBooksInShop();

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        }
    }
}