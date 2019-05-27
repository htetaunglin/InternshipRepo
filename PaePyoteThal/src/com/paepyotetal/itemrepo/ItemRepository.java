package com.paepyotetal.itemrepo;

import java.util.ArrayList;

import com.paepyotetal.model.Item;
import com.paepyotetal.model.ItemCategory;

public interface ItemRepository {
	void insertItemsInfo(Item item);

	void insertItemsList(ArrayList<Item> list);

	Item findItem(ItemCategory catory);

	int getSize();
}
