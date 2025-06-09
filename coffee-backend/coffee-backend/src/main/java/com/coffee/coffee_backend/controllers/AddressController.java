package com.coffee.coffee_backend.controllers;
import com.coffee.coffee_backend.entities.Address;
import com.coffee.coffee_backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public String listAddresses(Model model) {
        model.addAttribute("addresses", addressService.findAll());
        return "list-addresses";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("address", new Address());
        return "add-address";
    }

    @PostMapping("/save")
    public String saveAddress(@ModelAttribute("address") Address address) {
        addressService.save(address);
        return "redirect:/addresses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findById(id));
        return "edit-address";
    }

    @PostMapping("/update/{id}")
    public String updateAddress(@PathVariable Long id, @ModelAttribute Address address) {
        addressService.update(id, address);
        return "redirect:/addresses";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return "redirect:/addresses";
    }
}