package nl.youngcapital.backend.controller;

import nl.youngcapital.backend.entity.User;
import nl.youngcapital.backend.repository.UserRepository;
import nl.youngcapital.backend.security.CustomUserDetails;
import nl.youngcapital.backend.security.UserValidator;
import nl.youngcapital.backend.service.UserService;
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
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

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
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        String email = loggedUser.getEmail();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/change_details")
    public String changeUserDetails(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        String email = loggedUser.getEmail();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);
        return "change_details";
    }
}