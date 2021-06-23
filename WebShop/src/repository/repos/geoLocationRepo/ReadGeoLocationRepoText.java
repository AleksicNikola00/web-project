package repository.repos.geoLocationRepo;

import java.util.ArrayList;
import java.util.UUID;

import beans.model.GeoLocation;

public class ReadGeoLocationRepoText implements IReadGeoLocationRepo {
	private static String path;

	public ReadGeoLocationRepoText(String path) {
		this.path = path;
	}

	@Override
	public GeoLocation getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GeoLocation> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}