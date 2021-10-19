package nl.dijkstra.favorites.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private int year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Book(String title, String author, int year, User user) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.user = user;
    }
}
