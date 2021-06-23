package repository.repos.credentialsRepo;

import java.util.ArrayList;

import beans.model.Credentials;

public class ReadCredentialsRepoText implements IReadCredentialsRepo {
	private static String path;

	public ReadCredentialsRepoText(String path) {
		this.path = path;
	}

	@Override
	public Credentials getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Credentials> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}