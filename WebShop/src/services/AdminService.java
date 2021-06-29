package services;

import java.util.ArrayList;

import beans.model.Admin;
import beans.errors.*;

public class AdminService extends BaseService {
	
	public AdminService(String path) {
		super(path);
	}
	
	public String add(Admin admin) {
		Admin foundInDataBase = uow.getAdminReadRepo().getById(admin.getUsername());
		
		if (foundInDataBase == null) {
			uow.getAdminWriteRepo().add(admin);
			return DatabaseErrors.NO_ERROR;
		}
		
		if (foundInDataBase.isDeleted()) {
			uow.getAdminWriteRepo().update(admin);
			return DatabaseErrors.NO_ERROR;
		} else {
			return DatabaseErrors.ALREADY_EXISTS;
		}
	}
	
	public String update(Admin admin) {
		Admin foundInDataBase = uow.getAdminReadRepo().getById(admin.getUsername());
		
		if (foundInDataBase == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		uow.getAdminWriteRepo().update(admin);
		return DatabaseErrors.NO_ERROR;
	}
	
	public String delete(Admin admin) {
		Admin foundInDataBase = uow.getAdminReadRepo().getById(admin.getUsername());
		
		if (foundInDataBase == null) {
			return DatabaseErrors.NOT_FOUND;
		}
		
		if (foundInDataBase.isDeleted()) {
			return DatabaseErrors.ALREADY_DELETED;
		}
		
		uow.getAdminWriteRepo().delete(admin);
		return DatabaseErrors.NO_ERROR;
	}
	
	public Admin getById(String username) {
		return uow.getAdminReadRepo().getById(username);
	}
	
	public ArrayList<Admin> getAll(){
		return uow.getAdminReadRepo().getAll();
	}
}