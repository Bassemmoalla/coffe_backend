package com.coffee.coffee_backend.controllers.Rest;

import com.coffee.coffee_backend.entities.Address;
import com.coffee.coffee_backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestAddresses")
public class RestAddressController {

    @Autowired
    private AddressService addressService;

    /**
     * GET /RestAddresses
     *   → Returns a JSON array of all Address records.
     */
    @GetMapping
    public ResponseEntity<List<Address>> listAll() {
        List<Address> all = addressService.findAll();
        return ResponseEntity.ok(all);
    }

    /**
     * GET /RestAddresses/{id}
     *   → Returns a single Address (by ID) as JSON, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        Address found = addressService.findById(id);
        return ResponseEntity.ok(found);
    }

    /**
     * POST /RestAddresses
     *   → Expects a JSON body for a new Address, saves it, and returns the created Address (including its new ID).
     */
    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        Address created = addressService.save(address);
        return ResponseEntity.ok(created);
    }

    /**
     * PUT /RestAddresses/{id}
     *   → Expects a JSON body with updated fields, applies it to the existing Address with this ID,
     *     and returns the updated Address. If the ID doesn’t exist, your service layer should throw.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(
            @PathVariable Long id,
            @RequestBody Address updatedAddress) {
        Address updated = addressService.update(id, updatedAddress);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /RestAddresses/{id}
     *   → Deletes the Address with this ID. Returns 204 No Content on success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}