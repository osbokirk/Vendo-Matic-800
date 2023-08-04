package com.techelevator.view;

public class Chip extends item {
    private String message = "Crunch Crunch, Yum!";
    public Chip(String itemSlot, String itemName, String itemType, String itemPrice) {
        super(itemSlot, itemName, itemType, itemPrice);
    }
    public String getMessage(){
        return this.message;
    }
}
