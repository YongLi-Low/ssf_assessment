package iss.ibf.ssf_assessment.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ShippingAddress {
    
    @NotNull(message = "Please enter your name")
    @NotEmpty(message = "Please enter your name")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String username;

    @NotNull(message = "Please enter your address")
    @NotEmpty(message = "Please enter your address")
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
