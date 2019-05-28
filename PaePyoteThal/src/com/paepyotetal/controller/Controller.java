package com.paepyotetal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.paepyotetal.historyrepo.HistoryRepository;
import com.paepyotetal.historyrepo.HistoryRepositoryImpl;
import com.paepyotetal.itemrepo.ItemRepository;
import com.paepyotetal.itemrepo.ItemRepositoryImpl;
import com.paepyotetal.model.Item;
import com.paepyotetal.model.ItemCategory;
import com.paepyotetal.model.ItemForCount;
import com.paepyotetal.model.ItemForWeight;
import com.paepyotetal.model.SaleItem;
import com.paepyotetal.util.TheUtils;

public class Controller {

	private ItemRepository repo;
	private HistoryRepository historyRep;

	public Controller() {
		repo = ItemRepositoryImpl.getInstance();
		historyRep = HistoryRepositoryImpl.getInstance();
	}

	public void run() {
		showHeaderAndFooter(true);

		// insert Item
		repo.insertItemsList(getItemList());

		System.out.println("*************************");
		System.out.println("***** READY TO SALE *****");
		System.out.println("*************************");

		do {
			System.out.println();
			menuDetect(showMenu());
			System.out.println();
		} while (TheUtils.readString("Do you want to again? (y|n) : ").equalsIgnoreCase("y"));

		showHeaderAndFooter(false);
	}

	private static void showHeaderAndFooter(boolean flag) {
		System.out.println("*************************");
		if (flag)
			System.out.println("**** Pyae Pyote Thal ****");
		else
			System.out.println("******* Thank You *******");

		System.out.println("*************************");

	}

	private ArrayList<Item> getItemList() {
		ArrayList<Item> itemList = new ArrayList<>();

		int j = 1;
		for (ItemCategory category : ItemCategory.values()) {
			if (category.equals(ItemCategory.PYEPYOTE)) {
				ItemForWeight item = new ItemForWeight();
				item.setID(j++);
				item.setName(category.toString());
				item.setSmallUnit(TheUtils.readInt("Enter Minimum unit of " + category.toString() + " : "));
				item.setSmallUnitPrice(TheUtils
						.readInt("Enter " + category.toString() + "'s price per " + (int) item.getSmallUnit() + " : "));
				itemList.add(item);
			} else {
				ItemForCount item = new ItemForCount();
				item.setID(j++);
				item.setName(category.toString());
				item.setUnitPrice(TheUtils.readInt("Enter " + category.toString() + "'s price per one : "));
				itemList.add(item);
			}
		}
		return itemList;
	}

	private static int showMenu() {
		System.out.println("What do you want to do?");
		System.out.println("1. Add Sale History");
		System.out.println("2. Show Sale History");
		return TheUtils.readInt("Please enter your operation : ");
	}

	private void menuDetect(int i) {
		switch (i) {
		case 1:
			int itemID = getWantToHistoryItemID("Please select Item do you want to add.", false);
			if (itemID == 0)
				break;
			showCurrentHistory(
					historyRep.addHistory(repo.findItem(itemID), TheUtils.readInt("Please enter amount of Item : ")));
			break;
		case 2:
			int itemID2 = getWantToHistoryItemID("Please select Item to show history.", true);
			List<SaleItem> list = historyRep.getHistory(itemID2);
			showHistoryList(list);
			break;
		default:
			System.out.println("Wrong!");
			break;
		}
	}

	private void showCurrentHistory(SaleItem saleItem) {
		System.out.println("----------------------");

		System.out.println("Item\t: " + saleItem.getItem().getName());
		System.out.println("Amount\t: " + saleItem.getAmount());
		System.out.println("Total\t: " + saleItem.getItem().getTotal(saleItem.getAmount()));

		System.out.println("----------------------");
	}

	private int getWantToHistoryItemID(String title, boolean flag) {

		System.out.println();
		System.out.println(title);
		Arrays.asList(ItemCategory.values()).forEach(Controller::listPrint);
		i = 1;
		if (flag)
			System.out.println("If you see all please enter 0");
		else
			System.out.println("If you don't want to add please enter 0");

		int itemID = TheUtils.readInt("Please enter Item Id : ");
		return itemID;
	}

	static int i = 1;

	private static void listPrint(Object value) {
		System.out.println(i++ + ". " + value);
	}

	private void showHistoryList(List<SaleItem> list) {
		String titleF = "%2s%4s%-10s%10s%10s%n";
		String dataF = "%2d%4s%-10s%10d%10.1f%n";
		String totalF = "%-5s%31.1f%n";

		System.out.println("-------------------------------------");
		System.out.printf(titleF, "No", "", "Item", "Amount", "Total");
		System.out.println("-------------------------------------");
		if (list.size() == 0)
			System.out.printf("%23s%n", "Nothing found!"); // Nothing found!
		else
			for (SaleItem item : list) {
				System.out.printf(dataF, i++, "", item.getItem().getName(), item.getAmount(),
						item.getItem().getTotal(item.getAmount()));
			}
		System.out.println("-------------------------------------");
		System.out.printf(totalF, "Total", historyRep.getTotalSales(list));
		System.out.println("-------------------------------------");
	}

}
