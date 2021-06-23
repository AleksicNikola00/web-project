package repository.repos.managerRepo;

import java.util.ArrayList;

import beans.model.Manager;

public class ReadManagerRepoText implements IReadManagerRepo {
	private static String path;

	public ReadManagerRepoText(String path) {
		this.path = path;
	}

	@Override
	public Manager getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Manager> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}