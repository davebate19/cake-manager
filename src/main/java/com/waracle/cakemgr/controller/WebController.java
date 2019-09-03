package com.waracle.cakemgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/addCakes")
    public String addCakePage(Model model) {
        return "addCakes";
    }
}
