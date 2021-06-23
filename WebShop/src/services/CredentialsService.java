package services;

import beans.model.Credentials;

public class CredentialsService extends BaseService {
	
	public CredentialsService(String path) {
		super(path);
	}
	
	public void addCredentials(Credentials credentials) {
		uow.getCredentialsWriteRepo()
			.add(credentials);
	}

}