package nl.youngcapital.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String joke;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Joke(String joke, User user) {
        this.joke = joke;
        this.user = user;
    }

    public Joke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "joke='" + joke + '\'' +
                '}';
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }
}
