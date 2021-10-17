package nl.youngcapital.backend.service;

import nl.youngcapital.backend.entity.User;
import nl.youngcapital.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        boolean isUpdatingUser = user.getId() != null;

        if (isUpdatingUser) {
            User existingUser = userRepository.findById(user.getId()).get();

            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
            }
        }
        return userRepository.save(user);
    }

    public User updateAccount(User userInForm) {
        User userInDB = userRepository.findById(userInForm.getId()).get();

        if (!userInForm.getPassword().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userInForm.getPassword());
            userInDB.setPassword(encodedPassword);
        }

        userInDB.setEmail(userInForm.getEmail());
        userInDB.setName(userInForm.getName());

        return userRepository.save(userInDB);
    }
}
