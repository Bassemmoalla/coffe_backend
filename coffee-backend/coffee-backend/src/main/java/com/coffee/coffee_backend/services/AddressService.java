package com.coffee.coffee_backend.services;
import com.coffee.coffee_backend.entities.Address;
import com.coffee.coffee_backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Unable to find address with id " + id));
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address update(Long id, Address addressUpdate) {
        Address found = findById(id);
        found.setStreet(addressUpdate.getStreet());
        found.setCity(addressUpdate.getCity());
        found.setCountry(addressUpdate.getCountry());
        found.setZipCode(addressUpdate.getZipCode());
        return addressRepository.save(found);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public void deleteAll() {
        addressRepository.deleteAll();
    }
}