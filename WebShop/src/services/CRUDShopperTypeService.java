package services;

import beans.enumerations.TypeOfShopper;
import beans.errors.DatabaseErrors;
import beans.model.ShopperType;

public class CRUDShopperTypeService extends BaseService {
	
	public CRUDShopperTypeService(String path) {
		super(path);
	}
	
	public String add(ShopperType shopperType) {
		
		ShopperType databaseShopperType = uow.getShopperTypeReadRepo().getById(shopperType.getType());
		
		if (databaseShopperType == null) {
			uow.getShopperTypeWriteRepo().add(shopperType);
			return DatabaseErrors.NO_ERROR;
		}
		else {
			uow.getShopperTypeWriteRepo().update(shopperType);
			return DatabaseErrors.NO_ERROR;
		}
	}
	
	public ShopperType getShopperType(TypeOfShopper type) {
		return uow.getShopperTypeReadRepo().getById(type);
	}
}
