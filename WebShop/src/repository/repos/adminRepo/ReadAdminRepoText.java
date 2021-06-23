package repository.repos.adminRepo;

import java.util.ArrayList;

import beans.model.Admin;

public class ReadAdminRepoText implements IReadAdminRepo {
	private static String path;

	public ReadAdminRepoText(String path) {
		this.path = path;
	}
	
	@Override
	public Admin getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Admin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}