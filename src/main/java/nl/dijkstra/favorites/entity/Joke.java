package nl.dijkstra.favorites.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String favoriteJoke;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Joke(String favoriteJoke, User user) {
        this.favoriteJoke = favoriteJoke;
        this.user = user;
    }

    public Joke(String favoriteJoke) {
        this.favoriteJoke = favoriteJoke;
    }
}
