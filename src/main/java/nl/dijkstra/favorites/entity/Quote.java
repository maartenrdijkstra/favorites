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
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String favoriteQuote;

    @Column
    private String source;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Quote(String favoriteQuote, String source, User user) {
        this.favoriteQuote = favoriteQuote;
        this.source = source;
        this.user = user;
    }
}
