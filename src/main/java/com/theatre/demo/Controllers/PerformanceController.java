package com.theatre.demo.Controllers;

import com.theatre.demo.Helpers;
import com.theatre.demo.Models.Hall;
import com.theatre.demo.Models.Performance;
import com.theatre.demo.Models.Play;
import com.theatre.demo.Models.Playbill;
import com.theatre.demo.Repos.HallRepo;
import com.theatre.demo.Repos.PerformanceRepo;
import com.theatre.demo.Repos.PlayRepo;
import com.theatre.demo.Repos.PlaybillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
public class PerformanceController {
    @Autowired
    private PerformanceRepo performanceRepo;
    @Autowired
    private HallRepo hallRepo;
    @Autowired
    private PlayRepo playRepo;
    @Autowired
    private PlaybillRepo playbillRepo;

    @GetMapping("/performances")
    public String getPerformances(Map<String, Object> model) {

        Iterable<Performance> performances = performanceRepo.findAll();
        Iterable<Hall> halls = hallRepo.findAll();
        Iterable<Play> plays = playRepo.findAll();

        model.put("performances", performances);
        model.put("halls", halls);
        model.put("plays", plays);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "performances";
    }

    @GetMapping("/performances/{performanceId}")
    public String getPerformance(@PathVariable(name="performanceId") Long performanceId, Map<String, Object> model) {

        Performance performance = performanceRepo.getById(performanceId.intValue());

        Iterable<Hall> halls = hallRepo.findAll();
        Iterable<Play> plays = playRepo.findAll();

        model.put("performance", performance);
        model.put("halls", halls);
        model.put("plays", plays);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "performance";
    }

    @GetMapping("/performances/{performanceId}/remove")
    public String removePerformance(@PathVariable(name="performanceId") Long performanceId) {

        Performance performance = performanceRepo.getById(performanceId.intValue());
        performanceRepo.delete(performance);

        return "redirect:/performances";
    }

    @GetMapping("/performances/add")
    public String addPerformance(@RequestParam(name="date") String date, @RequestParam(name="hallId") Long hallId, @RequestParam(name="playId") Long playId) {

        Play play = playRepo.getById(playId.intValue());
        Hall hall = hallRepo.getById(hallId.intValue());

        Playbill playbill = new Playbill();
        Iterable<Playbill> playbills = playbillRepo.findAll();

        for(Playbill pb: playbills) {
            playbill = pb;
            break;
        }

        Performance performance = new Performance(date, hall, play, playbill);
        performanceRepo.save(performance);

        return "redirect:/performances";
    }

    @GetMapping("/performances/{performanceId}/edit")
    public String editPerformance(@PathVariable(name="performanceId") Long performanceId,
                                  @RequestParam(name="date") String date,
                                  @RequestParam(name="hallId") Long hallId,
                                  @RequestParam(name="playId") Long playId) {

        Performance performance = performanceRepo.getById(performanceId.intValue());
        Play play = playRepo.getById(playId.intValue());
        Hall hall = hallRepo.getById(hallId.intValue());

        performance.setDate(date);
        performance.setHall(hall);
        performance.setPlay(play);

        performanceRepo.save(performance);

        return "redirect:/performances/" + performanceId.toString();
    }
}
