package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    private CakeService cakeService;

    @GetMapping("/cakes")
    public String getJsonCakeList() throws JsonProcessingException {
        List<Cake> cakes = cakeService.getAllCakes();
        final ObjectMapper mapper = new ObjectMapper();
        return cakes != null ? mapper.writeValueAsString(cakes) : "No Cakes have been found";
    }

    @GetMapping("/cakes/cakeId={cakeId}")
    public String getCakeById(@PathVariable Long cakeId) throws JsonProcessingException {
        Optional<Cake> cake = cakeService.getCakeById(cakeId);
        final ObjectMapper mapper = new ObjectMapper();
        return cake.isPresent() ? mapper.writeValueAsString(cake.get()) : "Cake not found";
    }

    @GetMapping("/cakes/title={title}")
    public String getCakeByTitle(@PathVariable String title) throws JsonProcessingException {
        Optional<Cake> cake = cakeService.getCakeByTitle(title);
        final ObjectMapper mapper = new ObjectMapper();
        return cake.isPresent() ? mapper.writeValueAsString(cake.get()) : "Cake not found";
    }

    @PostMapping("/cakes")
    public void addNewCake(@RequestBody Cake cake) {
        cakeService.saveCake(cake);
    }
}
