package hello.model;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Book {

  //https://github.com/laurasnchezglez/micronaut-library-example

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @ManyToOne
    private Genre genre;

    public Book() {}

    public Book(@NotNull String name, @NotNull String isbn, @NotNull  Genre genre) {
        this.isbn = isbn;
        this.name = name;
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                '}';
    }

}
