package com.theatre.demo.Controllers;

import com.theatre.demo.Helpers;
import com.theatre.demo.Models.Performance;
import com.theatre.demo.Models.Playbill;
import com.theatre.demo.Repos.PlaybillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class PlaybillController {
    @Autowired
    private PlaybillRepo playbillRepo;

    @GetMapping("/playbill")
    public String playbill(Map<String, Object> model) {

        Iterable<Playbill> playbills = playbillRepo.findAll();
        List<Playbill> playbillList = new ArrayList<>();

        for(Playbill playbill: playbills) {
            playbillList.add(playbill);
        }

        Playbill playbill;
        List<Performance> performances;

        if(playbillList.size() == 0) {
            performances = new ArrayList<>();
            playbill = new Playbill(performances);
            playbillRepo.save(playbill);
        } else {
            playbill = playbillList.get(0);
            performances = playbill.getPerformances();
        }

        model.put("menu", Helpers.menu);
        model.put("performances", performances);
        model.put("links", Helpers.links);

        return "playbill";
    }
}
