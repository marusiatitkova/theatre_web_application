package com.theatre.demo.Repos;

import com.theatre.demo.Models.Performance;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepo extends CrudRepository<Performance, Long> {
    public Performance getById(Integer Id);
}
