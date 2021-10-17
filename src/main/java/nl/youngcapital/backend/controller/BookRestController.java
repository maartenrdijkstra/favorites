package nl.youngcapital.backend.controller;

import nl.youngcapital.backend.entity.Book;
import nl.youngcapital.backend.exception.ResourceNotFoundException;
import nl.youngcapital.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    // Perhaps not necessary
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Perhaps obsolete
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " does not exist."));
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " does not exist"));
        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
