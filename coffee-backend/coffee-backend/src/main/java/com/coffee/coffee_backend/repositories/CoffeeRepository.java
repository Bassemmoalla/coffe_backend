package com.coffee.coffee_backend.repositories;

import com.coffee.coffee_backend.entities.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}