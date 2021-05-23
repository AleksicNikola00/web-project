package beans;

import java.util.Date;

public class Shopper extends User{
	private int points;
	
	//TODO: add history of carts
	public Shopper() {
		
	}
	
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
	
	public String toString() {
		String out = super.toString();
		out += "\nPoints : " + this.points;
		return out;
	}
	
	public String format() {
		String out = super.format();
		
		out += ":" + this.points;
		
		return out;
	}
}
