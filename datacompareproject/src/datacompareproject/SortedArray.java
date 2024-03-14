/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 3
Due Date: Mar. 14, 2024

Source File: MainProgram.java
Class: SortedArray
*/

package datacompareproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;


public class SortedArray implements CCDatabase{
	private Account[] accounts;
	private int size;
	
    public SortedArray() {
    	this.size = 0;
        this.accounts = new Account[20];
    }
	
	public void readAccounts(String fileName) {
		CCDatabase database = new SortedArray();
		Duration duration = runTests(database, fileName);
		long ms = duration.toMillis();
		System.out.println("Sorted Array took: " + ms + " milliseconds.");
	}
	
	private static Duration runTests (CCDatabase database, String fileName) {
		Instant start = null;
		Instant end = null;
		long accountNumber;
		String name, address;
		double creditLimit, balance, newLimit, price;
		try (Scanner opsFile = new Scanner (new File ("databases/" + fileName))) {
			
			while (opsFile.hasNextLine()) {
				String operation = opsFile.nextLine().trim();
				
				switch (operation) {
				case "start":
					start = Instant.now();
					break;
				case "stop":
					end = Instant.now();
					break;
				case "cre":
					
				    accountNumber = opsFile.nextLong(); 
				    opsFile.nextLine();
    				name = opsFile.nextLine().trim();
    				address = opsFile.nextLine().trim();
    				creditLimit = opsFile.nextDouble();
    				opsFile.nextLine();
    				balance = opsFile.nextDouble();
    				opsFile.nextLine();
    				
    				database.createAccount(accountNumber, name, address, creditLimit, balance);
    				//System.out.println(database.getAccount(accountNumber) + "\n");
    				break;
    				
				case "del":
					accountNumber = opsFile.nextLong(); 
					opsFile.nextLine();
					database.deleteAccount(accountNumber);
					//System.out.println(database.getAccount(accountNumber) + "\n");
					break;
					
				case "lim":
					accountNumber = opsFile.nextLong(); 
					opsFile.nextLine();
					newLimit = opsFile.nextDouble();
					opsFile.nextLine();
					
					database.adjustCreditLimit(accountNumber, newLimit);
					//System.out.println(database.getAccount(accountNumber) + "\n");
					break;
					
				case "pur":
					accountNumber = opsFile.nextLong(); 
					opsFile.nextLine();
					price = opsFile.nextDouble();
					opsFile.nextLine();
					try {
						database.makePurchase(accountNumber, price);
					} catch (Exception e) {
						//System.out.println("Error making purchase: " + e.getMessage());
					}
					//System.out.println(database.getAccount(accountNumber) + "\n");
					break;
				}
				
			}
			opsFile.close();
			return Duration.between(start, end);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
			return null;
		}
	}

	public boolean createAccount(long accountNumber, String name, String address, double creditLimit, double balance) {
		int index = binarySearch(accountNumber);
        if (index >= 0) {
            return false;
        }
        
        index = -(index + 1);

        if (size == accounts.length) {
            growArray();
        }

        for (int i = size; i > index; i--) {
            accounts[i] = accounts[i - 1];
        }

        Account account = new Account();
        account.setCreditCard(accountNumber);
        account.setCardHolder(name);
        account.setAddress(address);
        account.setCreditLimit(creditLimit);
        account.setBalOwing(balance);

        accounts[index] = account;
        size++;

        return true;
	}

    private int binarySearch(long accountNumber) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            long midAccountNumber = accounts[mid].getCreditCard();

            if (midAccountNumber == accountNumber) {
                return mid;
            } else if (midAccountNumber < accountNumber) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -(low + 1);
    }
    
    private void growArray() {
        Account[] newArray = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newArray, 0, accounts.length);
        accounts = newArray;
    }

	public boolean deleteAccount(long accountNumber) {
		int index = binarySearch(accountNumber);
        if (index >= 0) {
            return false;
        }
        
        for (int i = index; i < size; i++) {
        	accounts[i] = accounts[i + 1];
        }
        
        accounts[--size] = null;
        
		return true;
	}

	public boolean adjustCreditLimit(long accountNumber, double newLimit) {
	    int index = binarySearch(accountNumber);
	    if (index < 0) {
	        return false;
	    }

	    accounts[index].setCreditLimit(newLimit);

	    return true;
	}

	public String getAccount(long accountNumber) {
		 int index = binarySearch(accountNumber);
		    if (index < 0) {
		        return null;
		    }
		    Account account = accounts[index];
		    return account.getCreditCard() + "\n" +
		           account.getCardHolder() + "\n" +
		           account.getAddress() + "\n" +
		           account.getCreditLimit() + "\n" +
		           account.getBalOwing();
	}

	public boolean makePurchase(long accountNumber, double price) throws Exception {
	    int index = binarySearch(accountNumber);
	    if (index < 0) {
	        return false;
	    }

	    Account account = accounts[index];
	    double balance = account.getBalOwing();
	    double creditLimit = account.getCreditLimit();

	    if (balance + price > creditLimit) {
	        throw new Exception("Insufficient funds for purchase");
	    }

	    account.setBalOwing(balance + price);

	    return true;
	}
}
