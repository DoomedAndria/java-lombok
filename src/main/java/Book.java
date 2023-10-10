import lombok.*;

@AllArgsConstructor
@Data
public class Book {
    private Integer id ;
    private String name;
    private String author;
    private Integer pages;
}
