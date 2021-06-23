package services;

import java.util.*;

public class BaseService {
	protected UnitOfWork uow;
	
	public BaseService(String path) {
		uow = new UnitOfWork(path);
	}

}