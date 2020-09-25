package com.theatre.demo.Repos;

import com.theatre.demo.Models.Play;
import org.springframework.data.repository.CrudRepository;

public interface PlayRepo extends CrudRepository<Play, Long> {
    public Play getById(Integer Id);
}
