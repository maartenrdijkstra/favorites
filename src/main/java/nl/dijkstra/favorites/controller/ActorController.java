package nl.dijkstra.favorites.controller;

import nl.dijkstra.favorites.entity.Actor;
import nl.dijkstra.favorites.entity.User;
import nl.dijkstra.favorites.repository.ActorRepository;
import nl.dijkstra.favorites.security.CustomUserDetails;
import nl.dijkstra.favorites.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @PostMapping("/deleteActor")
    public String deleteActor(@RequestParam Long id) {
        Actor actor = actorRepository.findById(id).get();
        actorRepository.delete(actor);

        return "redirect:/actors";
    }

    @GetMapping("actors")
    public String getActors(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model, @Param("keyword") String keyword) {

        model.addAttribute("pageTitle", "Favorite actors");
        model.addAttribute("keyword", keyword);
        User user = User.builder().id(loggedUser.getId()).build();
        model.addAttribute("userActors", actorService.getActorsByUser(user));

        if (keyword != null) {
            model.addAttribute("filteredActors", actorService.filterActors(keyword));
        }

        return "actors";
    }

    @PostMapping(value = "actors")
    public String addActorToUser(@RequestParam("name") String name,
                                 @AuthenticationPrincipal CustomUserDetails loggedUser) {
        Actor actorToAdd = Actor.builder()
                .name(name)
                .user(User.builder().id(loggedUser.getId()).build())
                .build();
        actorRepository.save(actorToAdd);

        return "redirect:/actors";
    }
}