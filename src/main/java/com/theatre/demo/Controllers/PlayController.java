package com.theatre.demo.Controllers;

import com.theatre.demo.Helpers;
import com.theatre.demo.Models.Actor;
import com.theatre.demo.Models.Performance;
import com.theatre.demo.Models.Play;
import com.theatre.demo.Repos.ActorRepo;
import com.theatre.demo.Repos.PerformanceRepo;
import com.theatre.demo.Repos.PlayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PlayController {
    @Autowired
    private PlayRepo playRepo;
    @Autowired
    private ActorRepo actorRepo;
    @Autowired
    private PerformanceRepo performanceRepo;

    @GetMapping("/plays")
    public String getPlays(Map<String, Object> model) {

        Iterable<Play> plays = playRepo.findAll();
        Iterable<Actor> actors = actorRepo.findAll();

        model.put("plays", plays);
        model.put("actors", actors);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "plays";
    }

    @GetMapping("/plays/add")
    public String getPlay(@RequestParam(name="playName") String playName, @RequestParam(name="actorIds[]") Long[] actorIds) {

        List<Actor> actors = new ArrayList<>();
        System.out.println("Actors:");
        for(Long actorId: actorIds) {
            Actor actor = actorRepo.getById(actorId.intValue());
            System.out.println(actorId.toString());
            actors.add(actor);
        }
        List<Performance> performances = new ArrayList<>();

        Play play = new Play(playName, actors, performances);
        playRepo.save(play);

        return "redirect:/plays";
    }

    @GetMapping("/plays/{playId}")
    public String getPlay(@PathVariable(name="playId") Long playId, Map<String, Object> model) {

        Play play = playRepo.getById(playId.intValue());
        Iterable<Actor> actors = actorRepo.findAll();

        model.put("play", play);
        model.put("actors", actors);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "play";
    }

    @GetMapping("/plays/{playId}/remove")
    public String removePlay(@PathVariable(name="playId") Long playId) {

        Play play = playRepo.getById(playId.intValue());

        List<Performance> performances = play.getPerformances();
        System.out.println(performances.toArray().toString());
        if(performances != null && performances.size() > 0) {
            for(Performance performance: performances) {
                performanceRepo.delete(performance);
            }
        }

        List<Actor> actors = play.getActors();
        actors.clear();

        play.setActors(actors);
        performances.clear();
        play.setPerformances(performances);
        playRepo.save(play);

        playRepo.delete(play);

        return "redirect:/plays";
    }

    @GetMapping("/plays/{playId}/edit")
    public String editPlay(
            @PathVariable(name="playId") Long playId,
            @RequestParam(name="playName") String playName,
            @RequestParam(name="actorIds[]") Long[] actorIds) {

        Play play = playRepo.getById(playId.intValue());

        play.setName(playName);

        List<Actor> actors = new ArrayList<>();

        for(Long actorId: actorIds) {
            Actor actor = actorRepo.getById(actorId.intValue());
            actors.add(actor);
        }
        play.setActors(actors);

        playRepo.save(play);

        return "redirect:/plays/" + playId.toString();
    }
}
