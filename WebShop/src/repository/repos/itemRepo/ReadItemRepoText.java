package repository.repos.itemRepo;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.Item;

public class ReadItemRepoText implements IReadItemRepo {
	private static String path;

	public ReadItemRepoText(String path) {
		this.path = path;
	}

	@Override
	public Item getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}