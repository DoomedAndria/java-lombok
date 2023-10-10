import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Book b1 = new Book(null,"godfather2","piuzo2",11242);
        BookUtil bu = BookUtil.getInstance();
        bu.deleteBook(2);
    }
}
