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
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String quote;

    @Column
    private String source;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Quote(String quote, String source, User user) {
        this.quote = quote;
        this.source = source;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quote='" + quote + '\'' +
                ", source='" + source + '\'' +
                '}';
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }
}
