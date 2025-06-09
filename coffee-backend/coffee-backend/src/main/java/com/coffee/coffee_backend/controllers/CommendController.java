package com.coffee.coffee_backend.controllers;

import com.coffee.coffee_backend.entities.Coffee;
import com.coffee.coffee_backend.entities.Commend;
import com.coffee.coffee_backend.entities.Customer;
import com.coffee.coffee_backend.services.CoffeeService;
import com.coffee.coffee_backend.services.CommendService;
import com.coffee.coffee_backend.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/commends")
public class CommendController {

    @Autowired
    private CommendService commendService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public String listCommends(Model model) {
        model.addAttribute("commends", commendService.findAll());
        return "list-commends";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // 1) Create an empty Commend
        model.addAttribute("commend", new Commend());
        // 2) Provide lists of all customers + coffees
        List<Customer> customers = customerService.findAll();
        List<Coffee> coffees = coffeeService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("coffees", coffees);
        return "add-commend";
    }

    @PostMapping("/save")
    public String saveCommend(@ModelAttribute("commend") Commend commend) {
        // If the user did not pick a date, set today's date
        if (commend.getDateCommend() == null) {
            commend.setDateCommend(new Date());
        }
        commendService.save(commend);
        return "redirect:/commends";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Commend existing = commendService.findById(id);
        model.addAttribute("commend", existing);
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("coffees", coffeeService.findAll());
        return "edit-commend";
    }

    @PostMapping("/update/{id}")
    public String updateCommend(@PathVariable Long id, @ModelAttribute Commend commend) {
        commendService.update(id, commend);
        return "redirect:/commends";
    }

    @GetMapping("/delete/{id}")
    public String deleteCommend(@PathVariable Long id) {
        commendService.deleteById(id);
        return "redirect:/commends";
    }
}