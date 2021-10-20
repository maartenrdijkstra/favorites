package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;


}
