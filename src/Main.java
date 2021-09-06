import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            DbConnection.openDbConnection();
        } catch (SQLException e){
            System.out.println("Error connecting to DB");
            e.printStackTrace();
        }
    }
}
