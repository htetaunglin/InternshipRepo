package com.paepyotetal.itemrepo;

import java.util.ArrayList;

import com.paepyotetal.model.Item;

public interface ItemRepository {
	void insertItemsInfo(Item item);

	void insertItemsList(ArrayList<Item> list);

	Item findItem(int itemID);

	int getSize();

}
