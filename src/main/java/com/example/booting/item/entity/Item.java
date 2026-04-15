package com.example.booting.item.entity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;


@Hidden
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Item(){
    }

    public Item(String name, Double price){
        this.name = name;
        this.price = price;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Item{id = " + id + ", name = " + name + ", price = " + price + "}";
    }
}
