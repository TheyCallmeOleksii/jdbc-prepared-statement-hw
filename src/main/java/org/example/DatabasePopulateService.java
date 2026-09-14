import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String sqlFilePath = "src/main/resources/sql/populate_db.sql";

        try {
            String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));

            Connection connection = Database.getInstance().getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.execute();
                System.out.println("Таблиці успішно наповнені даними!");
            }

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Помилка виконання SQL запиту: " + e.getMessage());
        }
    }
}