package com.coffee.coffee_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "coffees")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "coffee_type", nullable = false)
    private CoffeeType coffeeType;

    @Column(nullable = false)
    private Integer capacity;   // in grams or whatever unit

    @Column(name = "price_per_kg", nullable = false, precision = 10)
    private Double pricePerKg;

    public Coffee() {
    }

    public Coffee(CoffeeType coffeeType, Integer capacity, Double pricePerKg) {
        this.coffeeType = coffeeType;
        this.capacity = capacity;
        this.pricePerKg = pricePerKg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
}