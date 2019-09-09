package com.waracle.cakemgr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    public void initialiseDatabase() throws IOException {

        InputStream inputStream = this.getClass().getResourceAsStream("/cake.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Cake> cakes = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Cake.class));

        cakes.forEach(this::saveCake);
    }

    public void saveCake(Cake cake) {
        cakeRepository.save(cake);
    }

    public List<Cake> getAllCakes() {
        return cakeRepository.findAll();
    }

    public Optional<Cake> getCakeById(Long cakeId) {
        return cakeRepository.findById(cakeId);
    }

    public Optional<Cake> getCakeByTitle(String title) {
        return cakeRepository.findByTitle(title);
    }
}
