package com.theatre.demo.Controllers;

import com.theatre.demo.Helpers;
import com.theatre.demo.Models.Hall;
import com.theatre.demo.Models.Performance;
import com.theatre.demo.Repos.HallRepo;
import com.theatre.demo.Repos.PerformanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HallController {
    @Autowired
    private HallRepo hallRepo;
    @Autowired
    private PerformanceRepo performanceRepo;

    @GetMapping("/halls")
    public String getHalls(Map<String, Object > model) {

        Iterable<Hall> halls = hallRepo.findAll();

        model.put("halls", halls);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "halls";
    }

    @GetMapping("/halls/add")
    public String addHall(@RequestParam(name="hallName") String hallName) {

        List<Performance> performances = new ArrayList<>();
        Hall hall = new Hall(hallName, performances);

        hallRepo.save(hall);

        return "redirect:/halls";
    }

    @GetMapping("/halls/{hallId}")
    public String getHall(@PathVariable(name="hallId") Long hallId, Map<String, Object> model) {

        Hall hall = hallRepo.getById(hallId.intValue());

        model.put("hall", hall);
        model.put("menu", Helpers.menu);
        model.put("links", Helpers.links);

        return "hall";
    }

    @GetMapping("/halls/{hallId}/remove")
    public String removeHall(@PathVariable(name="hallId") Long hallId) {

        Hall hall = hallRepo.getById(hallId.intValue());

        List<Performance> performances = hall.getPerformances();

        for(Performance performance: performances) {
            performanceRepo.delete(performance);
        }

        hallRepo.delete(hall);

        return "redirect:/halls";
    }

    @GetMapping("/halls/{hallId}/edit")
    public String editHall(@PathVariable(name="hallId") Long hallId,
                           @RequestParam(name="hallName") String hallName) {

        Hall hall = hallRepo.getById(hallId.intValue());

        hall.setName(hallName);

        hallRepo.save(hall);

        return "redirect:/halls/" + hallId.toString();
    }

}
