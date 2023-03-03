package iss.ibf.ssf_assessment.models;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Item implements Serializable{
    
    @NotNull(message = "Item name cannot be empty")
    @NotEmpty(message = "Item name cannot be empty")
    private String name;

    @Min(value = 1, message = "You must add at least 1 item")
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", quantity=" + quantity + "]";
    }
    
}
