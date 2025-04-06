package org.example.spring25;


import org.example.spring25.domain.PlaygroundService;
import org.example.spring25.domain.entity.Playground;
import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {

    private final PlaygroundService playgroundService;

   // private final PlaygroundRepository playgroundRepository;
    public HelloController(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @GetMapping("/hello")
    String hello(Model model) {
        model.addAttribute("message","Hello, World!");
        model.addAttribute("name", "Anna");
        return "hello";
    }
/*
    @GetMapping("/${lang}/hello")
    String hello(Model model, @PathVariable("lang") String lang) {
        model.addAttribute("message","Hello, World!");
        model.addAttribute("name", "Anna");
        return "hello";
    }
    Istället för att jobba med cookies
    */

    @GetMapping("/playgrounds")
    @ResponseBody
    List<Playground> playground(Model model) {
        return playgroundService.getAllPlaygrounds();
    }


}
