package com.paepyotetal.historyrepo;

import java.util.List;

import com.paepyotetal.model.Item;
import com.paepyotetal.model.SaleItem;

public interface HistoryRepository {
	SaleItem addHistory(Item item, int amount);

	List<SaleItem> getHistory(int itemID);

	int getSize();

}
