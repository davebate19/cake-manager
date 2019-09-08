package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CakeRepository extends JpaRepository<Cake, Long> {
    Optional<Cake> findByTitle(String title);
}
