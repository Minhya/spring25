package org.example.spring25.web.controller;

import ch.qos.logback.core.model.Model;
import org.example.spring25.domain.PlaygroundService;
import org.example.spring25.domain.entity.Playground;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
//public class PlaygroundController {
//
//    private final PlaygroundService playgroundService;
//    public PlaygroundController(PlaygroundService playgroundService) {
//        this.playgroundService = playgroundService;
//    }
//
//    @GetMapping("/view")
//    public String viewPlaygrounds(Model model) {
//        model.addAttribute("playgrounds", playgroundService.getAllPlaygrounds());
//        return "view-playgrounds";
//    }
//
//    @GetMapping("/add")
//    public String showAddPlaygroundForm(Model model) {
//        //Add an empty playground object to the model for the form to bin
//        if(!model.containsAttribute("playground")){
//            model.addAttribute("playgound", new Playground());
//        }
//    return "add-playground";
//    }
//
//    @PostMapping("/add")
//    public String addPlayground(@ModelAttribute("playground") Playground playground) {
//        playgroundService.addPlayground(playground);
//        return "redirect:/playgrounds";
//    }
//
//}
