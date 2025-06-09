package com.coffee.coffee_backend.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "commends")
public class Commend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // *Tell Spring to expect "yyyy-MM-dd" from the form*
    @Column(name = "date_commend", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCommend;

    @Column(name = "total_price", nullable = false, precision = 10)
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;

    public Commend() {
    }

    public Commend(Date dateCommend, Double totalPrice, Customer customer, Coffee coffee) {
        this.dateCommend = dateCommend;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.coffee = coffee;
    }

    // … getters and setters …
    public Long getId() {
        return id;
    }

    public Date getDateCommend() {
        return dateCommend;
    }

    public void setDateCommend(Date dateCommend) {
        this.dateCommend = dateCommend;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
}