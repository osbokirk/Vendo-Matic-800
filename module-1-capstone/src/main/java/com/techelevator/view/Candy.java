package com.techelevator.view;

public class Candy extends item {
    private String message = "Munch Munch, Yum!";
    public Candy(String itemSlot, String itemName, String itemType, String itemPrice) {
        super(itemSlot, itemName, itemType, itemPrice);
    }
    public String getMessage(){
        return this.message;
    }
}
