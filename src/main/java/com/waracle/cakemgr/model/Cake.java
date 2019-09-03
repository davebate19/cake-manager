package com.waracle.cakemgr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Data
public class Cake implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue
    private int id;

    private String title;

    private String desc;

    private String image;

}