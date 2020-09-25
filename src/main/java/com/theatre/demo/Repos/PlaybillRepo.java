package com.theatre.demo.Repos;

import com.theatre.demo.Models.Playbill;
import org.springframework.data.repository.CrudRepository;

public interface PlaybillRepo extends CrudRepository<Playbill, Long> {
}
