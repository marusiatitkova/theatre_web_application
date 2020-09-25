package com.theatre.demo.Repos;

import com.theatre.demo.Models.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepo extends CrudRepository<Actor, Long> {
    public Actor getById(Integer id);
}
