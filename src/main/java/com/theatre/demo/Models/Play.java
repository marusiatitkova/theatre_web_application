package com.theatre.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Play {
    @Id
    @GeneratedValue
    @Column(name="playId")
    private Integer id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "play_actors", joinColumns = @JoinColumn(name = "playId"),
            inverseJoinColumns = @JoinColumn(name = "actorId"))
    private List<Actor> actors;

    @OneToMany(targetEntity = Performance.class, mappedBy = "play", fetch = FetchType.EAGER)
    List<Performance> performances;

    public Play() {
    }

    public Play(String name, List<Actor> actors, List<Performance> performances) {
        this.name = name;
        this.actors = actors;
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

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
