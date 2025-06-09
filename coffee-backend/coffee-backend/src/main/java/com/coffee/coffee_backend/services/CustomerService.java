package com.coffee.coffee_backend.services;

import com.coffee.coffee_backend.entities.Address;
import com.coffee.coffee_backend.entities.Customer;
import com.coffee.coffee_backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Unable to find customer with id " + id));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer update(Long id, Customer customerUpdate) {
        Customer found = findById(id);
        found.setFirstName(customerUpdate.getFirstName());
        found.setLastName(customerUpdate.getLastName());
        found.setEmail(customerUpdate.getEmail());
        found.setPhoneNumber(customerUpdate.getPhoneNumber());

        // Update nested address
        Address addr = found.getAddress();
        addr.setStreet(customerUpdate.getAddress().getStreet());
        addr.setCity(customerUpdate.getAddress().getCity());
        addr.setCountry(customerUpdate.getAddress().getCountry());
        addr.setZipCode(customerUpdate.getAddress().getZipCode());
        return customerRepository.save(found);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public Customer deleteAddress(Long customerId) {
        Customer found = findById(customerId);
        Address addr = found.getAddress();
        found.setAddress(null);
        addressService.deleteById(addr.getId());
        return customerRepository.save(found);
    }
}