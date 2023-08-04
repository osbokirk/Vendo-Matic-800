package com.techelevator.view;

import static java.lang.Double.parseDouble;

public class item {

    protected String itemSlot;

    private String itemName;

    private String itemType;

    protected double itemPrice;

    private int itemQuantity;
    //Double moneyCounter
    public item(String itemSlot,String itemName,String itemType,String itemPrice){
       this.itemSlot = itemSlot;
       this.itemName = itemName;
       this.itemType = itemType;
       this.itemQuantity= 5;
       //item price must parse to a double
       this.itemPrice = parseDouble(itemPrice);
    }

    public String toString() {
        //SOLD OUT INDICATOR
        if (itemQuantity == 0)
            return String.format("%-8s%-20s$%-8.2fSOLD OUT", itemSlot, itemName, itemPrice);
        return String.format("%-8s%-20s$%-8.2f%d", itemSlot, itemName, itemPrice, itemQuantity);
    }

    public String getItemName(){
        return this.itemName;
    }
    public double getItemPrice(){
        return this.itemPrice;
    }
    public int getItemQuantity(){
        return this.itemQuantity;
    }
    public String getItemSlot(){
        return this.itemSlot;
    }

    public String getMessage(){
        return ("Enjoy The Trip To Flavor Town");
    }

    public String vend(CashDrawer cashDrawer){
       if (this.itemQuantity > 0){
           this.itemQuantity -= 1;
       return("");
       }else {
           return ("Sorry Were Sold Out,But We Have Other  ");
       }
    }
}
