package com.coffee.coffee_backend.controllers;

import com.coffee.coffee_backend.entities.Coffee;
import com.coffee.coffee_backend.entities.CoffeeType;
import com.coffee.coffee_backend.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    // 1) List all coffees
    @GetMapping
    public String listCoffees(Model model) {
        model.addAttribute("coffees", coffeeService.findAll());
        return "list-coffees";
    }

    // 2) Show "Add Coffee" form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("coffee", new Coffee());
        // Provide the enum values so Thymeleaf can render a dropdown
        model.addAttribute("coffeeTypes", CoffeeType.values());
        return "add-coffee";
    }

    // 3) Save new coffee
    @PostMapping("/save")
    public String saveCoffee(@ModelAttribute("coffee") Coffee coffee) {
        coffeeService.save(coffee);
        return "redirect:/coffees";
    }

    // 4) Show Edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("coffee", coffeeService.findById(id));
        model.addAttribute("coffeeTypes", CoffeeType.values());
        return "edit-coffee";
    }

    // 5) Update coffee
    @PostMapping("/update/{id}")
    public String updateCoffee(@PathVariable Long id, @ModelAttribute Coffee coffee) {
        coffeeService.update(id, coffee);
        return "redirect:/coffees";
    }

    // 6) Delete coffee
    @GetMapping("/delete/{id}")
    public String deleteCoffee(@PathVariable Long id) {
        coffeeService.deleteById(id);
        return "redirect:/coffees";
    }
}