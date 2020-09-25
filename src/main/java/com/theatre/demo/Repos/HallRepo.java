package com.theatre.demo.Repos;

import com.theatre.demo.Models.Hall;
import lombok.Lombok;
import org.springframework.data.repository.CrudRepository;

public interface HallRepo extends CrudRepository<Hall, Long> {
    public Hall getById(Integer Id);
}
