package com.coffee.coffee_backend.repositories;

import com.coffee.coffee_backend.entities.Commend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommendRepository extends JpaRepository<Commend, Long> {
}
