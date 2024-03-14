/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 3
Due Date: Mar. 14, 2024

Source File: MainProgram.java
*/

package datacompareproject;

public class Account {
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
	
}
