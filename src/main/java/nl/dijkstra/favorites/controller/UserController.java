package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.exception.ResourceNotFoundException;
import nl.dijkstra.favorites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    // Perhaps not necessary
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Perhaps obsolete
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " does not exist."));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " does not exist."));
        userDetails.setId(user.getId());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " does not exist."));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
