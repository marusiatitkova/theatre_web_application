package com.theatre.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Playbill {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(targetEntity = Performance.class, mappedBy = "playbill", fetch = FetchType.EAGER)
    List<Performance> performances;

    public Playbill() {
    }

    public Playbill(List<Performance> performances) {
        this.performances = performances;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
