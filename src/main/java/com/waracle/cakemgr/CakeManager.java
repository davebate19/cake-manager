package com.waracle.cakemgr;

import com.waracle.cakemgr.service.CakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CakeManager implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CakeManager.class);

    @Autowired
    private CakeService cakeService;

    public static void main(String[] args) {
        SpringApplication.run(CakeManager.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            cakeService.initialiseDatabase();
        } catch (IOException e) {
            log.error("Failed to initialise the database:\n {}", e.getMessage());
        }
    }
}
