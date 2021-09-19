public interface SqlQueries {

    String QUERY_ADD_NEW_BOOK = "INSERT INTO BOOKS (Title, Author_first_name, Author_last_name, Genre, Publication_date, Available_books) VALUES (?,?,?,?,?,?)";
    String QUERY_ADD_NEW_BOOK_TO_SHOP = "INSERT INTO LIST_OF_BOOKS (Book_id, Price) VALUES ((SELECT Book_id FROM BOOKS WHERE Title = ?), ?)";

    String QUERY_GET_ALL_BOOKS = "SELECT Book_id, Title, Author_first_name, Author_last_name, Genre, Publication_date, Available_books FROM BOOKS";
    String QUERY_GET_ALL_BOOKS_IN_SHOP = "SELECT LIST_OF_BOOKS.List_id, LIST_OF_BOOKS.Book_id, LIST_OF_BOOKS.Price, BOOKS.Title, BOOKS.Available_books " +
            "FROM LIST_OF_BOOKS JOIN BOOKS ON LIST_OF_BOOKS.Book_id = BOOKS.Book_id";
    String QUERY_GET_ALL_AVAILABLE_BOOKS_IN_SHOP = "SELECT LIST_OF_BOOKS.List_id, LIST_OF_BOOKS.Book_id, LIST_OF_BOOKS.Price, BOOKS.Title, BOOKS.Available_books " +
            "FROM LIST_OF_BOOKS JOIN BOOKS ON LIST_OF_BOOKS.Book_id = BOOKS.Book_id WHERE Available_books != 0 ";
    String QUERY_GET_USED_TITLES = "SELECT Title FROM BOOKS";
    String QUERY_GET_NUMBER_OF_AVAILABLE_BOOKS_BY_ID = "SELECT Available_books FROM BOOKS WHERE Book_id = ";

    String QUERY_REMOVE_BOOK_BY_ID_FROM_LIST = "DELETE FROM LIST_OF_BOOKS WHERE Book_id = ?";
    String QUERY_REMOVE_BOOK_BY_ID = "DELETE FROM BOOKS WHERE Book_id = ?";

    String QUERY_SET_NUMBER_OF_AVAILABLE_BOOKS_BY_ID = "UPDATE BOOKS SET Available_books = ? WHERE Book_id = ?";
}
