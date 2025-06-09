package com.coffee.coffee_backend.services;

import com.coffee.coffee_backend.entities.Commend;
import com.coffee.coffee_backend.repositories.CommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommendService {

    @Autowired
    private CommendRepository commendRepository;

    public Commend save(Commend commend) {
        return commendRepository.save(commend);
    }

    public Commend findById(Long id) {
        return commendRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Unable to find commend with id " + id));
    }

    public List<Commend> findAll() {
        return commendRepository.findAll();
    }

    public Commend update(Long id, Commend commendUpdate) {
        Commend found = findById(id);
        found.setDateCommend(commendUpdate.getDateCommend());
        found.setTotalPrice(commendUpdate.getTotalPrice());
        found.setCustomer(commendUpdate.getCustomer());
        found.setCoffee(commendUpdate.getCoffee());
        return commendRepository.save(found);
    }

    public void deleteById(Long id) {
        commendRepository.deleteById(id);
    }

    public void deleteAll() {
        commendRepository.deleteAll();
    }
}