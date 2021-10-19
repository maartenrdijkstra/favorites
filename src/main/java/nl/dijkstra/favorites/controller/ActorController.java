package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Actor;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.mapper.ActorMapper;
import nl.dijkstra.favorites.repository.ActorRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/actors")
    public String getUserActors(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userActors", actorRepository.getActorsByUser(user));
        model.addAttribute("pageTitle", "Favorite actors");

        return "actors";
    }

    @PostMapping("/deleteActor")
    public String deleteActor(@RequestParam Long id) {
        Actor actor = actorRepository.findById(id).get();
        actorRepository.delete(actor);

        return "redirect:/actors";
    }

    @GetMapping("/actorsFiltered")
    public String getActorsFiltered(Model model, String keyword) throws IOException {
        model.addAttribute("allActors", ActorMapper.getAllActors());
        model.addAttribute("pageTitle", "Actors Filtered");

        return "actorsFiltered";
    }
//
//    @GetMapping("/listActors")
//    public String listActors(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size) {
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//
//        Page<Actor> actorPage
//    }

}
