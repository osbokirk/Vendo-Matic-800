package com.techelevator.view;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLITests {
    @Test
    public void chipGetMessage() {
        item testItem = new Chip("A1", "Potato Crisps", "Chip", "3.05");
        String expected = "Crunch Crunch, Yum!";
        String actual = testItem.getMessage();
        Assert.assertEquals("Wrong message displayed", expected,actual);

    }
    @Test
    public void CandyGetMessage() {
        item testItem = new Candy("B1", "Moonpie", "Candy", "1.80");
        String expected = "Munch Munch, Yum!";
        String actual = testItem.getMessage();
        Assert.assertEquals("Wrong message displayed", expected,actual);

    }
    @Test
    public void drinkGetMessage() {
        item testItem = new Drink("C1", "Cola", "Drink", "1.25");
        String expected = "Glug Glug, Yum!";
        String actual = testItem.getMessage();
        Assert.assertEquals("Wrong message displayed", expected,actual);

    }
    @Test
    public void gumGetMessage() {
        item testItem = new Gum("D1", "U-Chews", "Gum", "0.85");
        String expected = "Chew Chew, Yum!";
        String actual = testItem.getMessage();
        Assert.assertEquals("Wrong message displayed", expected,actual);

    }

}