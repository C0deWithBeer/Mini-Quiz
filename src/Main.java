import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        try {
            manager.showMenu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
