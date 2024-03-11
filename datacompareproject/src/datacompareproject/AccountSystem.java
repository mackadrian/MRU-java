package datacompareproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class AccountSystem implements CCDatabase{
	private long creditCard;
	private String cardHolder;
	private String address;
	private double creditLimit;
	private double balOwing;
	
	public long getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getCardHolder() {
		return cardHolder;
	}
	
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	public double getBalOwing() {
		return balOwing;
	}
	
	public void setBalOwing(double balOwing) {
		this.balOwing = balOwing;
	}
	
	
	
	
	public void readAccounts(String fileName) {
		try (Scanner scan = new Scanner(new File("databases/" + fileName))) {
			while(scan.hasNext()) {
				scan.nextLine();
				setCreditCard(scan.nextLong());
				setCardHolder(scan.nextLine());
				setAddress(scan.nextLine());
				setCreditLimit(scan.nextDouble());
				setBalOwing(scan.nextDouble());
			}
			
			
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean createAccount(long accountNumber, String name, String address, double creditLimit, double balance) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteAccount(long accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean adjustCreditLimit(long accountNumber, double newLimit) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAccount(long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean makePurchase(long accountNumber, double price) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
}
