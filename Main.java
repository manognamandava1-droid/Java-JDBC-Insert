import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "your_password";

        try (Connection con =
                 DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "email VARCHAR(100))");

            String sql =
                "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, 1);
                ps.setString(2, "Ravi");
                ps.setString(3, "ravi@gmail.com");
                ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setString(2, "Sita");
                ps.setString(3, "sita@gmail.com");
                ps.executeUpdate();
            }

            System.out.println("Two users inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
