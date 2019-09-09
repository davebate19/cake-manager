package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cake implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String desc;

    private String image;
}