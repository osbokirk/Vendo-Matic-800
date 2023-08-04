package com.techelevator;

import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class VendingMachineCLI {
	private Menu menu;
	//Variables for the Strings Used in the Main Menu
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";

	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";

	private static final String MAIN_MENU_OPTION_EXIT = "Exit";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String P_MENU_CURRENT_MONEY = "Current Money Provided: ";

	//Variables for the Strings Used in the Purchase Menu
	private static final String P_MENU_FEED_MONEY = "Feed Money";

	private static final String P_MENU_SELECT = "Select Product";

	private static final String P_MENU_FINISH = "Finish Transaction";
	private static final String[] P_MENU_OPTIONS = { P_MENU_FEED_MONEY, P_MENU_SELECT, P_MENU_FINISH };
	List<item> allItems;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public List<item> itemOptions(){								//Checks Item and type from csv &&
		Scanner input = new Scanner(System.in);						//generates the appropriate item
		String filePath = "vendingmachine.csv";
		File enteredFile = new File(filePath);
		item c;
		List<item> allItems = new ArrayList<>();
		try (Scanner fileInput = new Scanner(enteredFile)) {
			while (fileInput.hasNext()){
				String line = fileInput.nextLine();
				String[] a = line.split("\\|");
				if (a[3].equalsIgnoreCase("Chip")){
					c = new Chip(a[0], a[1], (a[3]), a[2]);
				}else if (a[3].equalsIgnoreCase("Drink")){
					c = new Drink(a[0], a[1], (a[3]), a[2]);
				}else if (a[3].equalsIgnoreCase("Gum")){
					c = new Gum(a[0], a[1], (a[3]), a[2]);
				}else if (a[3].equalsIgnoreCase("Candy")){
					c = new Candy(a[0], a[1], (a[3]), a[2]);
				}else {
					c = new item(a[0], a[1], (a[3]), a[2]);
				}
				allItems.add(c);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return allItems;
	}
	public void run() throws FileNotFoundException {

		allItems = itemOptions();

		CashDrawer cashDrawer = new CashDrawer();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println("Slot Id Product Name        Price    Available");
				System.out.print("----------------------------------------------");
				item[] itemsArray = allItems.toArray(new item[0]);
				menu.displayMenuOptions(itemsArray);

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String choice2;
				boolean looping = true;
				do {
					do {
						System.out.printf("\n%s $%.2f \n", P_MENU_CURRENT_MONEY, cashDrawer.getBalance());
						choice2 = (String) menu.getChoiceFromOptions(P_MENU_OPTIONS);

						if (choice2.equals(P_MENU_FEED_MONEY)) {
							System.out.print("Enter amount: ");
							Scanner scanner = new Scanner(System.in);
							try {
								double input = scanner.nextDouble();
								cashDrawer.addMoney(input);
								//System.out.printf("\n%s $%.2f \n", P_MENU_CURRENT_MONEY, cashDrawer.getBalance());
							} catch (InputMismatchException e) {
								System.out.println("Invalid input. Please enter a valid dollar amount.");
							}

						} else if (choice2.equals(P_MENU_SELECT)) {
							System.out.printf("\n%s $%.2f \n", P_MENU_CURRENT_MONEY, cashDrawer.getBalance());
							System.out.println("Slot Id Product Name        Price    Available");
							System.out.print("----------------------------------------------");
							item[] itemsArray = allItems.toArray(new item[0]);
							menu.displayMenuOptions(itemsArray);
							System.out.print("\nPlease select a product >>> ");
							Scanner scanner = new Scanner(System.in);

							try {
								String input = scanner.next().toUpperCase();
								boolean itemFound = false;
								int indexGrabber = 0;
								//do {
								for (int x = 0; (x < allItems.size()) && itemFound == false; x++) {
									if (allItems.get(x).getItemSlot().equals(input)) {
										indexGrabber = x;
										itemFound = true;
									}
								}
								cashDrawer.removeMoney(allItems.get(indexGrabber));
							} catch (NoSuchElementException e) {
								System.out.println("Invalid input. Please enter a valid selection.");
							}
						} else if (choice2.equals(P_MENU_FINISH)) {
							cashDrawer.closeDrawer();
							cashDrawer.returnedChange();
							looping = false;
							return;
						}
					}while(looping == true);
				}while(choice2.equals(P_MENU_FINISH) && looping == true);
			} else
				// exit
				return;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
