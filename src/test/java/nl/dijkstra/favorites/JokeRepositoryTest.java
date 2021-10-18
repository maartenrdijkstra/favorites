//package nl.dijkstra.favorites;
//
//import nl.dijkstra.favorites.entity.Joke;
//import nl.dijkstra.favorites.entity.User;
//import nl.dijkstra.favorites.repository.JokeRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class JokeRepositoryTest {
//
//
//    @Autowired
//    private JokeRepository jokeRepository;
//
//
//
//    Joke joke = Joke.builder()
//            .joke("A lame joke!")
//            .user(User.builder().id(1L).build())
//            .build();
//
//    @Test
//    public void testJokeResitory() {
//
//        Joke savedJoke = jokeRepository.save(joke);
//
//        System.out.println(savedJoke.getUser().getEmail());
//
//
//    }
//
//    @Test
//    public void testJokeUser() {
//        System.out.println(jokeRepository.findById(4L).get().getUser().getName());
//    }
//
//    @Test
//    public void testJokesByUser() {
//        System.out.println(jokeRepository.getJokesByUser(User.builder().id(1L).build()));
//    }
//}
