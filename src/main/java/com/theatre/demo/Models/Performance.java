package com.theatre.demo.Models;

import javax.persistence.*;

@Entity
public class Performance {
    @Id
    @GeneratedValue
    @Column(name="performanceId")
    private Integer id;

    private String date;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private Play play;

    @ManyToOne
    private Playbill playbill;

    public Performance() {
    }

    public Performance(String date, Hall hall, Play play, Playbill playbill) {
        this.date = date;
        this.hall = hall;
        this.play = play;
        this.playbill = playbill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public Playbill getPlaybill() {
        return playbill;
    }

    public void setPlaybill(Playbill playbill) {
        this.playbill = playbill;
    }
}
