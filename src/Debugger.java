import java.sql.SQLException;

public class Debugger {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBHandler dbHandler = new DBHandler();
        dbHandler.login("test","123");
    }
}
