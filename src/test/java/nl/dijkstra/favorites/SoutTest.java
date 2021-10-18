//package nl.youngcapital.backend;
//
//import nl.youngcapital.backend.entity.User;
//import nl.youngcapital.backend.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class SoutTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void printUserById() {
//       User user = userRepository.findById(1L).get();
//        System.out.println(user.getName());
//    }
//
//    @Test
//    public void printUserByEmail() {
//        System.out.println(userRepository.findByEmail("marty@gmail.com"));
//    }
//}
