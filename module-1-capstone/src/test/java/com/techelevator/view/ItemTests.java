package com.techelevator.view;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.io.FileNotFoundException;
import java.util.List;

public class ItemTests {
    @Test
    public void getMessage(){
        item testItem = new item("C1","Cola", "drink", "1.25");
        String expected = "Enjoy The Trip To Flavor Town";
        String actual = testItem.getMessage();
        Assert.assertEquals("Wrong message printed",expected,actual);
    }

    @Test
    public void vend() throws FileNotFoundException {
        item testItem = new item("C1","Cola", "drink", "1.25");
        CashDrawer testyTest = new CashDrawer();
        int expected = 4;
        testItem.vend(testyTest);
        int actual = testItem.getItemQuantity();
        Assert.assertEquals("Wrong message printed",expected,actual);
    }
}
