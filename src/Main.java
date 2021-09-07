import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Book book1 = new Book("Harry Potter", "J.K.", "Rowling");
        book1.setGenre("Novel");

        Bookshop bookshop = new Bookshop();

//        System.out.println("Adding new book: " + book1.getTitle());
//        bookshop.addNewBook(book1);

        System.out.println("\nShow all books in Bookshop: ");
        bookshop.showAllBooksInShop();

        System.out.println("\nShow all books: ");
        book1.showAllBooks();

//        System.out.println("\nRemove book by id");
//        bookshop.removeBookById(6);
    }
}
