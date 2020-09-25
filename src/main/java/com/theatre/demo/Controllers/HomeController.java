package com.theatre.demo.Controllers;

import com.theatre.demo.Helpers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Map<String, Object> model) {

        model.put("links", Helpers.links);
        model.put("menu", Helpers.menu);


        return "home";
    }
}
