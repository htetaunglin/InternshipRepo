package com.paepyotetal.historyrepo;

import java.util.ArrayList;
import java.util.List;

import com.paepyotetal.model.Item;
import com.paepyotetal.model.SaleItem;

public class HistoryRepositoryImpl implements HistoryRepository {

	static HistoryRepositoryImpl historyRepo;
	List<SaleItem> historylist;

	private HistoryRepositoryImpl() {
		historylist = new ArrayList<>();
	}

	public static HistoryRepositoryImpl getInstance() {
		if (historyRepo == null)
			historyRepo = new HistoryRepositoryImpl();
		return historyRepo;
	}

	@Override
	public SaleItem addHistory(Item item, int amount) {
		SaleItem saleItem = new SaleItem();
		saleItem.setItem(item);
		saleItem.setAmount(amount);
		historylist.add(saleItem);
		return saleItem;
	}

	@Override
	public int getSize() {
		return historylist.size();
	}

	@Override
	public List<SaleItem> getHistory(int itemID) {
		if (itemID == 0)
			return historylist;

		List<SaleItem> list = new ArrayList<>();
		for (SaleItem saleItem : historylist) {
			if (saleItem.getItem().getID() == itemID)
				list.add(saleItem);
		}
		return list;
	}

}
