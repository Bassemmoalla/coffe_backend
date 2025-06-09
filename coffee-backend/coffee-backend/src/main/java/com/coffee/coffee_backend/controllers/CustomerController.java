package com.coffee.coffee_backend.controllers;

import com.coffee.coffee_backend.entities.Customer;
import com.coffee.coffee_backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1) List all customers
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "list-customers";
    }

    // 2) Show "Add Customer" form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    // 3) Save new customer
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // Because cascade = CascadeType.ALL is on Address, the address object inside customer will be persisted automatically
        customerService.save(customer);
        return "redirect:/customers";
    }

    // 4) Show Edit form for existing customer
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit-customer";
    }

    // 5) Update customer
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customerService.update(id, customer);
        return "redirect:/customers";
    }

    // 6) Delete customer
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}