package com.techelevator.view;

public class Drink extends item{
    private String message = "Glug Glug, Yum!";
    public Drink(String itemSlot, String itemName, String itemType, String itemPrice) {
        super(itemSlot, itemName, itemType, itemPrice);
    }
    public String getMessage(){
        return this.message;
    }
}
