package nl.dijkstra.favorites.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String movieCharacter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public MovieCharacter(String movieCharacter, User user) {
        this.movieCharacter = movieCharacter;
        this.user = user;
    }

    @Override
    public String toString() {
        return "MovieCharacter{" +
                "movieCharacter='" + movieCharacter + '\'' +
                '}';
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }
}
