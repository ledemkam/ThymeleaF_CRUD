package org.example.thymeleaf_springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Year;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)//This annotation is used to map the enum type to the database column, and it is used to specify the EnumType.STRING value.
    @NotNull(message = "Product type cannot be null")
    private ProductType productType;

    @NotBlank(message = "Brand cannot be null")
    private String brand;

    @NotBlank(message = "Model cannot be null")
    private String model;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")//This annotation is used to validate that the value is positive.
    private double price;

    @NotNull(message = "Year cannot be null")
    private Year year;

    public Product() {
    }


    public Product(Long id, ProductType productType, String brand, String model, double price, Year year) {
        this.id = id;
        this.productType = productType;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Product type cannot be null") ProductType getProductType() {
        return productType;
    }

    public void setProductType(@NotNull(message = "Product type cannot be null") ProductType productType) {
        this.productType = productType;
    }

    public @NotBlank(message = "Brand cannot be null") String getBrand() {
        return brand;
    }

    public void setBrand(@NotBlank(message = "Brand cannot be null") String brand) {
        this.brand = brand;
    }

    public @NotBlank(message = "Model cannot be null") String getModel() {
        return model;
    }

    public void setModel(@NotBlank(message = "Model cannot be null") String model) {
        this.model = model;
    }

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")
    public double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be null") @Positive(message = "Price must be greater than zero") double price) {
        this.price = price;
    }

    public @NotNull(message = "Year cannot be null") Year getYear() {
        return year;
    }

    public void setYear(@NotNull(message = "Year cannot be null") Year year) {
        this.year = year;
    }
}
