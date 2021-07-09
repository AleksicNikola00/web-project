package services;

import beans.model.Manager;

public class ManagerService extends BaseService {

	public ManagerService(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public Manager getManager(String username) {
		return uow.getManagerReadRepo().getById(username);
	}

}
