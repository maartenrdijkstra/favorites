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
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String country;

    @Column
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Country(String country, String countryCode, User user) {
        this.country = country;
        this.countryCode = countryCode;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }
}