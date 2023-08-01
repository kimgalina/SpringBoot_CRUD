package com.example.SpringBoot_CRUD.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Product {
    int id;
    @NotEmpty(message ="Product name should not be empty")
    @Size(min=3,max=30,message="name of product should be between 3 and 30 characters ")
    private String productName;

    @NotEmpty(message ="Product cost should not be empty")
    @Min(0)
    private float cost;

    @NotEmpty(message ="Product provider should not be empty")
    @Size(min=5,max=30,message="name of provider should be between 5 and 30 characters ")
    private String provider;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
