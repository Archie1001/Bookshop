import jdk.jshell.execution.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            Book book1 = new Book("Harry Potter 2", "J.K.", "Rowling", "Fantasy", 2002, 6, 10.50);
//            book1.setGenre("Novel");

            Bookshop bookshop = new Bookshop();

//            bookshop.addNewBook(book1);

//            RandomBook.addRandomBook();

            bookshop.sellBookById(5, 1);
            book1.showAllBooks();
            bookshop.showAllBooksInShop();

//            bookshop.refundBookById(5);
//            bookshop.showAllBooksInShop();

        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        }
    }
}