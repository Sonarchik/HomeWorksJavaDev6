import org.flywaydb.core.Flyway;

public class DataMigrationService {
    private static final String CONNECTION_URL = "jdbc:h2:./test";
    private static final String CONNECTION_USER = null;
    private static final String CONNECTION_PASSWORD = null;

    public static void main(String[] args) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD)
                .load();

        flyway.migrate();
    }
}
