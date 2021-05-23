package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import beans.PotentialUser;
import beans.Shopper;
import beans.User;

/***
 * <p>Klasa namenjena da uèita korisnike iz fajla i pruža operacije nad njima (poput pretrage).
 * Korisnici se nalaze u fajlu WebContent/users.txt u obliku: <br>
 * firstName;lastName;email;username;password</p>
 * <p><b>NAPOMENA:</b> Lozinke se u praksi <b>nikada</b> ne snimaju u èistu tekstualnom obliku.</p>
 * @author Lazar
 *
 */
public class UserDAO {
	private ArrayList<PotentialUser> potentialUsers;
	private ArrayList<Shopper> shoppers;
	
	private String contextPath;
	
	private static String dataBasePath;
	
	public UserDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Može se pristupiti samo iz servleta.
	 */
	
	public UserDAO(String contextPath) {
		this.contextPath = contextPath;
		dataBasePath = contextPath + File.separator + "WEB-INF" + File.separator + "DataBase";
		potentialUsers = new ArrayList<PotentialUser>();
		loadCredentials();
		
		shoppers = new ArrayList<Shopper>();
		loadShoppers();
		
	}
	
	
	/**
	 * Vraæa korisnika za prosleðeno korisnièko ime i šifru. Vraæa null ako korisnik ne postoji
	 * @param username
	 * @param password
	 * @return
	 */
	public User find(String username, String password) {
		
		return null;
	}
	
	public Collection<PotentialUser> findAllPotentialUsers() {
		return potentialUsers;
	}
	
	/**
	 * Uèitava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #users}.
	 * Kljuè je korisnièko ime korisnika.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadCredentials() {
		try {
			
			String path = dataBasePath + File.separator + "credentials.txt";
			
			File credentialsDB = new File(path);
			Scanner reader = new Scanner(credentialsDB);
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				
				String[] usrpass = data.split(":");
				
				PotentialUser pu = new PotentialUser(usrpass[0], usrpass[1], beans.Role.values()[Integer.parseInt(usrpass[2])]);
				potentialUsers.add(pu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loading credentials not successful...");
		}
		System.out.println("Credentials size : " + potentialUsers.size());
	}
	
	private void loadShoppers() {
		try {
			
			String path = dataBasePath + File.separator +  "shoppers.txt";
			
			File shoppersDB = new File(path);
			Scanner reader = new Scanner(shoppersDB);
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				
				String[] input = data.split(":");
				
				int[] parsedDate = parseDate(input[6]);
				Shopper temp = new Shopper(input[0], input[1], input[2], input[3], input[4], new Date(parsedDate[0], parsedDate[1], parsedDate[2]), Integer.parseInt(input[7]));
				
				shoppers.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loading shoppers not successful...");
		}
		
		System.out.println("Shopper size : " + shoppers.size());
	}
	
	private int[] parseDate(String str) {
		int[] ret = new int[3];
		
		String[] parts = str.split("-");
		
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);
		
		ret[0] = year;
		ret[1] = month;
		ret[2] = day;
		
		return ret;
	}
	
	public PotentialUser checkIfExists(PotentialUser pu) {
		
		for (PotentialUser p : potentialUsers) {
			if (p.getUsername().equals(pu.getUsername()) && p.getPassword().equals(pu.getPassword())) {
				return p;
			}
		}
		
		return null;
		
	}
	
	public Shopper findShopper(String username, String password) {
		for(Shopper sp : shoppers) {
			if (sp.getUsername().equals(username) && sp.getPassword().equals(password)) {
				return sp;
			}
		}
		return null;
	}
	
	public boolean checkUsername(Shopper newShopper) {
		for(Shopper sp : shoppers) {
			if (sp.getUsername().equals(newShopper.getUsername())) {
				return false;
			}
		}
		return true;
	}
	
	public void saveCredentials(PotentialUser newUser) {
		this.potentialUsers.add(newUser);
		
		String path = contextPath + "DataBase" + File.separator + "credentials.txt";
		
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(newUser.format());
			
			System.out.println("credentials saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerShopper(Shopper newShopper) {
		//Create credentials
		saveCredentials(new PotentialUser(newShopper.getUsername(), newShopper.getPassword(), newShopper.getRole()));
		
		//Create shopper
		shoppers.add(newShopper);
		
		String path = contextPath + "DataBase" + File.separator + "shoppers.txt";
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(newShopper.format());
			
			System.out.println("shopper saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
