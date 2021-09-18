import java.sql.*;

public class Book implements SqlQueries {

    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String genre;

    private int publicationDate;
    private int availableBooks;
    private double price;

    Connection connection = null;

    public Book(String title, String authorFirstName, String authorLastName, String genre, int publicationDate, int availableBooks, double price) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.availableBooks = availableBooks;
        this.price = price;
    }

    public String getTitle() {
        if (title == null) {
            return "N/A";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        if (authorFirstName == null) {
            return "N/A";
        }
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        if (authorLastName == null) {
            return "N/A";
        }
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getGenre() {
        if (genre == null) {
            return "N/A";
        }
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationDate() {
        if (publicationDate == 0) {
            return -1;
        }
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getAvailableBooks() {
        if (availableBooks == 0) {
            return -1;
        }
        return availableBooks;
    }

    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }

    public double getPrice() {
        if (price == 0) {
            return -1;
        }
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void showAllBooks() throws SQLException {

        try {
            connection = DbConnection.createDbConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_BOOKS);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            System.out.println("Show all books: \n");

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
                String title = resultSet.getString("Title");
                String authorFirstName = resultSet.getString("Author_first_name");
                String authorLastName = resultSet.getString("Author_last_name");
                String genre = resultSet.getString("Genre");
                int publicationDate = resultSet.getInt("Publication_date");
                int availableBooks = resultSet.getInt("Available_books");

                System.out.println(bookId + " | " + title + " | " + authorFirstName + " | " + authorLastName + " | " + genre + " | " + publicationDate + " | " + availableBooks);
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

}