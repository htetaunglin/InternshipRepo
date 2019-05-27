package com.paepyotetal.itemrepo;

import java.util.ArrayList;

import com.paepyotetal.model.Item;
import com.paepyotetal.model.ItemCategory;

public class ItemRepositoryImpl implements ItemRepository {
	private static ItemRepositoryImpl repo;
	private static ArrayList<Item> itemList;

	private ItemRepositoryImpl() {
		itemList = new ArrayList<>();
	}

	public static ItemRepository getInstance() {
		if (repo == null)
			repo = new ItemRepositoryImpl();
		return repo;
	}

	@Override
	public void insertItemsInfo(Item item) {
		itemList.add(item);
	}

	@Override
	public int getSize() {
		return itemList.size();
	}

	@Override
	public Item findItem(ItemCategory catory) {
		for (Item item : itemList) {
			if (item.getName().equals(catory.toString()))
				return item;
		}
		return null;
	}

	@Override
	public void insertItemsList(ArrayList<Item> list) {
		if (itemList.isEmpty())
			itemList = list;
	}

}
