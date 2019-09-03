package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private CakeService cakeService;

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/cakes")
    public String getJsonCakeList() throws JsonProcessingException {
        List<Cake> cakes = cakeService.getAllCakes();
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cakes);
    }

    @PostMapping("/cakes")
    public void addNewCake(@RequestBody Cake cake) {
        cakeService.saveCake(cake);
    }
}
