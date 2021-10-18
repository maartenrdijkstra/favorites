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
public class MovieOrTvSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @Column
    private String year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public MovieOrTvSeries(String title, String year, User user) {
        this.title = title;
        this.year = year;
        this.user = user;
    }

    @Override
    public String toString() {
        return "MovieOrTvSeries{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }
}

