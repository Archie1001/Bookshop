import java.sql.*;

public class DbConnection {

    private static final String jdbcUrl = "jdbc:sqlite:C:\\Users\\amaka\\IdeaProjects\\Bookshop\\Bookshop.db";

    public static Connection createDbConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }
}


