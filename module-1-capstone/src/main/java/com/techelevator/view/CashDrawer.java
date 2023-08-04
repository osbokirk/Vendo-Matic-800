package com.techelevator.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CashDrawer {
    static double balance = 0.00;
    //Constant var for change values
    private final double QUARTERS = 0.25;
    private final double DIMES = 0.10;
    private final double NICKEL = 0.05;

    //Var used for storing numbers of each coins provided
    private int quaterReturned;

    private int nickelReturned;

    private int dimesReturned;

    private double startingBalanceLog;                  // var used to store balance value before transaction
    private PrintWriter logger;

    //Class variables needed for Printing Purposes
    LocalDateTime now = LocalDateTime.now();
    String pattern = "MM-dd-yyyy HH:mm:ss aa";
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    String date = dateFormat.format(new Date());
    DecimalFormat d = new DecimalFormat("'$'0.00");    //Imported and used DecimalFormat class to format dollar amounts

    public CashDrawer() throws FileNotFoundException {
        File log = new File("Transactions.txt");
        logger = new PrintWriter(log);
    }

    public static double getBalance(){
        return balance;
    }

    public void closeDrawer(){
        startingBalanceLog = balance;
        quaterReturned = (int) (balance/QUARTERS);
        balance -= (quaterReturned* QUARTERS);
        dimesReturned= (int) (balance/DIMES);
        balance -= (dimesReturned*DIMES);
        nickelReturned = (int) (balance/NICKEL);
        balance -= (nickelReturned*NICKEL);
        logger.println(date +" " + "GIVE CHANGE: "+ d.format(startingBalanceLog) + "  " + d.format(balance));
        logger.flush();
    }

    public void addMoney(double money){
        startingBalanceLog = balance;
        balance += money;
        logger.println(date +" " + "FEED MONEY: "+ d.format(startingBalanceLog) + "  " + d.format(balance));
        logger.flush();
    }

    public void removeMoney(item item) {
        if (balance < item.getItemPrice()){
            System.out.println("******* Sorry Invalid Funds *******");
        } else if (item.getItemQuantity() < 1) {
            System.out.println("******* Sorry Were Sold Out *******");
        } else {
            startingBalanceLog = balance;
            balance -= item.getItemPrice();
            item.vend(this);
            System.out.println("Item: " + item.getItemName() + "\nPrice: " + d.format(item.getItemPrice()) + "\nBalance: " + d.format(CashDrawer.balance) + "\n" + item.getMessage() ) ;
            logger.println(date + " " + item.getItemName() + " " + item.getItemSlot() + " " + d.format(item.itemPrice) + "  " + d.format(balance));
            logger.flush();
        }
    }

    public String returnedChange(){
        System.out.println("Here's Your Change of " + d.format(startingBalanceLog) + " in "+ quaterReturned +" quarters "+ dimesReturned + " dimes " + nickelReturned+ " nickels ");
        return("Here's Your Change of " + startingBalanceLog + " in "+ quaterReturned +" quarters "+ dimesReturned + " dimes " + nickelReturned+ " nickels ");
    }



}
