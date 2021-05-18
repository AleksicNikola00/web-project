package beans;

import java.util.Date;

public class Shopper extends User{
	private int points;
	
	//TODO: add history of carts
	
	public Shopper(String firstName, String lastName, String username, String password, String gender, Date dateOfBirth, int points) {
		super(firstName, lastName, username, password, gender, Role.SHOPPER ,dateOfBirth);
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int newPoints) {
		points = newPoints;
	}
}
