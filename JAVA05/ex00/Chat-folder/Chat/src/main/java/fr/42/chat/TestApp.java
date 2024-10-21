import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestApp {
    // Existing code...

    public static void testDatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5432/yourdatabase";
        String user = "youruser";
        String password = "yourpassword";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM \"User\"")) {
            
            while (rs.next()) {
                System.out.println("User ID: " + rs.getLong("id"));
                System.out.println("Login: " + rs.getString("login"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Call the test method
        testDatabaseConnection();
    }
}
