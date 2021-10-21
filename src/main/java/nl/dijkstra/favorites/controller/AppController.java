package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.repository.UserRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import nl.dijkstra.favorites.security.UserValidator;
import nl.dijkstra.favorites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("")
    public String viewHomePage(Model model, @AuthenticationPrincipal CustomUserDetails loggedUser) {
        model.addAttribute("pageTitle", "Welcome - Favorites App");
        model.addAttribute("loggedIn", loggedUser);
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Favorites - Sign Up");

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        model.addAttribute("pageTitle", "Sign up succeeded");
        return "register_success";
    }

    @PostMapping("change_details/update")
    public String saveDetails(User user, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal CustomUserDetails loggedUser) throws Exception {

        service.updateAccount(user);

        loggedUser.setName(user.getName());
        loggedUser.setPassword(user.getPassword());
        loggedUser.setEmail(user.getEmail());

        redirectAttributes.addFlashAttribute("message", "Your account details have been updated");

        return "redirect:/change_details";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("pageTitle", "Login - Favorites App");
        return "login";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        String email = loggedUser.getEmail();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Favorites App - Home Page");
        return "home";
    }

    @GetMapping("/change_details")
    public String changeUserDetails(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        String email = loggedUser.getEmail();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Change User Details");
        return "change_details";
    }
}