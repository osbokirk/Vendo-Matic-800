package com.techelevator.view;

public class Gum extends item{
    private String message = "Chew Chew, Yum!";
    public Gum(String itemSlot, String itemName, String itemType, String itemPrice) {
        super(itemSlot, itemName, itemType, itemPrice);
    }
    public String getMessage(){
        return this.message;
    }
}
