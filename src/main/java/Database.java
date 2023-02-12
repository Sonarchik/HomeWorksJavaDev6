import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database INSTANCE = new Database();
    private static final String CONNECTION_URL = "jdbc:h2:./test";

    private Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(CONNECTION_URL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
