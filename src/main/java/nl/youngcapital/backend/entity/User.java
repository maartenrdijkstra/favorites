package nl.youngcapital.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 35, name = "email")
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Transient
    private String retypePassword;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    public User(String email, String password, String retypePassword, String name) {
        this.email = email;
        this.password = password;
        this.retypePassword = retypePassword;
        this.name = name;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
