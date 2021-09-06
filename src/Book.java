public class Book {

    final private String title;
    final private String author;
    private String genre;

    private int bookId;
    private int pulicationDate;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        if (title == null){
            System.out.println("No value");
        }
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPulicationDate() {
        return pulicationDate;
    }

    public void setPulicationDate(int pulicationDate) {
        this.pulicationDate = pulicationDate;
    }
}
