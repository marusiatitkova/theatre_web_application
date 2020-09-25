package com.theatre.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hall {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(targetEntity = Performance.class, mappedBy = "hall", fetch = FetchType.EAGER)
    private List<Performance> performances;

    public Hall() {
    }

    public Hall(String name, List<Performance> performances) {
        this.name = name;
        this.performances = performances;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
