import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBook implements SqlQueries{

    private static final List<String> randomTitle = Arrays.asList("Grey Wolf", "Alice in Wonderland", "If we were villains", "Wild Embers",
            "Dark souls", "Beauty and the Beast", "A mind to murder", "Hide and Seek", "Live beautiful", "Praetorian", "First 90 Days",
            "It", "The 5 second rule", "Dare to Lead: Brave Work", "The Pizza Bible", "Animalium", "A Touch of Darkness",
            "365 Bedtime stories", "Funeral Moon", "Verity", "Measure what matters", "The Miniaturist", "Out of my mind",
            "A Man and His Watch", "Gothic", "True Believer", "American Sniper", "House of Cards", "Mad about Bugs", "King Arthur");
    private static final List<String> randomAuthorFirstName = Arrays.asList("John", "Paul", "Mark", "Andrew", "Artur", "Henry", "Nick", "Gavin",
            "Margaret", "Deborah", "Sarah", "Marie", "Sandra", "Lin", "Roger", "Stephen", "Tanya", "Jonathan", "Patrick", "Simon");
    private static final List<String> randomAuthorLastName = Arrays.asList("King", "Taylor", "Scarrow", "Simons", "Jordison", "Payne", "Stewart",
            "McDonald", "Heidegger", "Clark", "Higgins", "Dumbledore", "Snow", "Eckert", "Hale", "Grant", "Jones", "Walton", "Burton",
            "Follet", "Schneider", "Stickdorn", "Ember", "Asimov", "Johnson", "Oakley", "Jordan", "Bale", "Morgan", "Shore", "Fry");
    private static final List<String> randomGenre = Arrays.asList("Novel", "Science Fiction", "Horror", "Fantasy", "Document", "Thriller", "Romance",
            "History", "Reference", "Education & Training", "Cookbook", "Sport & Outdoor", "Travel", "Business & Money");

    static Connection connection = null;

    private static String getRandomString(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    private static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private static double getRandomNumber(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static void addRandomBook() throws SQLException {
        String title = getRandomString(randomTitle);
        String authorFirstName = getRandomString(randomAuthorFirstName);
        String authorLastName = getRandomString(randomAuthorLastName);
        String genre = getRandomString(randomGenre);

        int publicationDate = getRandomNumber(1900, 2022);
        int availableBooks = getRandomNumber(1, 151);
        double price = getRandomNumber(1.0, 99.99);
        price = price * 100;
        price = Math.round(price);
        price = price / 100; // rounding price to 2 decimal places

        System.out.print("New random book: ");
        System.out.println(title + " " + authorFirstName + " " + authorLastName + " " + genre
                + " " + publicationDate + " " + availableBooks + " " + price);

        if (Utils.checkUsedTitles(title)){
            System.out.println("Title is already used\n");
        } else {
            Utils.executeQueryAddNewBook(title, authorFirstName, authorLastName, genre, publicationDate, availableBooks, price);
        }
    }
}