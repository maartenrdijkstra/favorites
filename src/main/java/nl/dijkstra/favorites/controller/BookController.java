package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Book;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.BookMapper;
import nl.dijkstra.favorites.repository.BookRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("books")
    public String getUserBooks(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model, @Param("keyword") String keyword) throws IOException {
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userBooks", bookRepository.getBooksByUser(user));
        model.addAttribute("pageTitle", "Favorite books");
        model.addAttribute("keyword", keyword);

        List<Book> books = new ArrayList<>();

        if (keyword != null) {
            books = BookMapper.getBooks(keyword);
        }

        model.addAttribute("filteredBooks", books);

        return "books";
    }

    @PostMapping(value = "books")
    public String addBookToUser(@RequestParam("title") String title,
                                @RequestParam("author") String author,
                                @AuthenticationPrincipal CustomUserDetails loggedUser) {
        Book book = Book.builder()
                .title(title)
                .author(author)
                .user(User.builder()
                        .id(loggedUser.getId())
                        .build())
                .build();

        bookRepository.save(book);

        return "redirect:/books";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam Long id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);

        return "redirect:/books";
    }
}