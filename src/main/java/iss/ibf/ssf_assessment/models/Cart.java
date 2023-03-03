package iss.ibf.ssf_assessment.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private List<Item> contents = new ArrayList<>();

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }
    
    public void addItemToCart(Item item) {

        boolean isItemInContents = false;
        for (Item existingItem: contents) {
            if (existingItem.getName().equals(item.getName())) {
                isItemInContents = true;
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            }
        }
        if (!isItemInContents) {
            this.contents.add(item);
        }
    }

    public List<String> getCartItemsToList(List<Item> contents) {

        List<String> listOfItems = new ArrayList<>();
        for (Item item: contents) {
            listOfItems.add(item.getName());
        }
        return listOfItems;
    }

    @Override
    public String toString() {
        return "Cart:" + contents + "]";
    }
    
}
