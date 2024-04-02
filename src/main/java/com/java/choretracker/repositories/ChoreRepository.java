package com.java.choretracker.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.choretracker.models.Chore;

@Repository
public interface ChoreRepository extends CrudRepository<Chore, Long> {
}
