package com.theatre.demo.Controllers;

import com.theatre.demo.Helpers;
import com.theatre.demo.Models.Actor;
import com.theatre.demo.Models.Play;
import com.theatre.demo.Repos.ActorRepo;
import com.theatre.demo.Repos.PlayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ActorController {
    @Autowired
    private ActorRepo actorRepo;
    @Autowired
    private PlayRepo playRepo;

    @GetMapping("/actors")
    public String getActors(Map<String, Object> model) {

        Iterable<Actor> actors = actorRepo.findAll();

        model.put("actors", actors);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "actors";
    }

    @GetMapping("/actors/add")
    public String addActor(@RequestParam(name="actorName") String actorName,
                           @RequestParam(name="actorAge") Long actorAge) {

        Actor actor = new Actor(actorName, actorAge.intValue());
        actorRepo.save(actor);

        return "redirect:/actors";
    }

    @GetMapping("/actors/{actorId}")
    public String getActor(@PathVariable(name="actorId") Long actorId, Map<String, Object> model) {

        Actor actor = actorRepo.getById(actorId.intValue());

        model.put("actor", actor);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "actor";
    }

    @GetMapping("/actors/{actorId}/remove")
    public String removeActor(@PathVariable(name="actorId") Long actorId) {

        Actor actor = actorRepo.getById(actorId.intValue());
        Iterable<Play> plays = playRepo.findAll();
        for(Play play: plays) {
            if(play.getActors().contains(actor)) {
                play.getActors().remove(actor);
                playRepo.save(play);
            }
        }

        actorRepo.delete(actor);

        return "redirect:/actors";
    }

    @GetMapping("/actors/{actorId}/edit")
    public String editActor(
            @PathVariable(name="actorId") Long actorId,
            @RequestParam(name="actorName") String actorName,
            @RequestParam(name="actorAge") Long actorAge) {

        Actor actor = actorRepo.getById(actorId.intValue());

        actor.setName(actorName);
        actor.setAge(actorAge.intValue());

        actorRepo.save(actor);

        return "redirect:/actors/" + actorId.toString();
    }
}
