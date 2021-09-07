import java.sql.*;

public class Book {

    private static final String QUERY_FOR_GET_ALL_BOOKS = "SELECT * FROM BOOKS";

    final private String title;
    final private String authorFirstName;
    final private String authorLastName;
    private String genre;

    private int bookId;
    private int publicationDate;

    Connection connection = null;

    public Book(String title, String authorFirstName, String authorLastName) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        if (title == null) {
            System.out.println("No value");
        }
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void showAllBooks() throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_FOR_GET_ALL_BOOKS);

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
                int bookId = resultSet.getInt("Book_id");
                String title = resultSet.getString("title");
                String authorFirstName = resultSet.getString("author_first_name");
                String authorLastName = resultSet.getString("author_last_name");
                String genre = resultSet.getString("genre");
                int publicationDate = resultSet.getInt("publication_date");

                System.out.println(bookId + " | " + title + " | " + authorFirstName + " | " + authorLastName + " | " + genre + " | " + publicationDate);
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

}
