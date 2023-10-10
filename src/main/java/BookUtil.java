import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookUtil {
    private static BookUtil bu;
    private static final Statement statement = DB_connector.getStatement();

    private BookUtil() {
    }

    public static BookUtil getInstance() {
        if (bu == null)
            bu = new BookUtil();
        return bu;
    }

    public void insertBook(Book book) throws SQLException {
        String sql = String.format("INSERT INTO books (name, author, pages)VALUES('%s','%s',%d)"
                , book.getName(), book.getAuthor(), book.getPages());
        statement.executeUpdate(sql);
    }

    public void updateBook(int id, Book book) throws SQLException {
        String sql = String.format("UPDATE books SET name = '%s', author = '%s', pages = %d WHERE id = %d "
                , book.getName(), book.getAuthor(), book.getPages(), id);
        statement.executeUpdate(sql);
    }

    public Collection<Book> getAllBook() throws SQLException {
        String sql = "SELECT * from books";
        ResultSet rs = statement.executeQuery(sql);
        List<Book> books = new ArrayList<>();
        while(rs.next()){
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            Integer pages = rs.getInt("pages");
            books.add(new Book(id,name,author,pages));
        }
        return  books;
    }

    public Optional<Book> getBook(int id) throws SQLException {
        String sql = "SELECT * from books WHERE id = "+id;
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()){
            Integer id1 = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            Integer pages = rs.getInt("pages");
            return Optional.of(new Book(id1, name, author, pages));
        }
        return Optional.empty();
    }

    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id="+id;
        if(statement.executeUpdate(sql)==1){
            System.out.println("deleted successfully");
        }else{
            System.out.println("row could not found");
        }
    }


}
