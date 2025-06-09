package com.coffee.coffee_backend.services;

import com.coffee.coffee_backend.entities.Coffee;
import com.coffee.coffee_backend.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee save(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public Coffee findById(Long id) {
        return coffeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Unable to find coffee with id " + id));
    }

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Coffee update(Long id, Coffee coffeeUpdate) {
        Coffee found = findById(id);
        found.setCoffeeType(coffeeUpdate.getCoffeeType());
        found.setCapacity(coffeeUpdate.getCapacity());
        found.setPricePerKg(coffeeUpdate.getPricePerKg());
        return coffeeRepository.save(found);
    }

    public void deleteById(Long id) {
        coffeeRepository.deleteById(id);
    }

    public void deleteAll() {
        coffeeRepository.deleteAll();
    }
}