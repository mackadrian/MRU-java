package datacompareproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class HashMap implements CCDatabase {
	private Account[] accounts;
    private boolean[] deleted;
    private int size;
    
    public HashMap() {
    	this.size = 0;
        this.accounts = new Account[20];
        this.deleted = new boolean[20];
        
    }
    
	public void readAccounts(String fileName) {
		CCDatabase database = new HashMap();
		Duration duration = runTests(database, fileName);
		long ms = duration.toMillis();
		System.out.println("HashMap took: " + ms + " milliseconds.");
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
    
    private void growArray() {
        Account[] newArray = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newArray, 0, accounts.length);
        accounts = newArray;
    }
    
    private int hash(long accountNumber) {
    	String accountNumberStr = String.format("%016d", accountNumber);

    	short c1 = Short.parseShort(accountNumberStr.substring(0, 4));
    	short c2 = Short.parseShort(accountNumberStr.substring(4, 8));
    	short c3 = Short.parseShort(accountNumberStr.substring(8, 12));
    	short c4 = Short.parseShort(accountNumberStr.substring(12, 16));

        int BASE = 17;
        int hashValue = (int)(BASE*c1 + Math.pow(BASE, 2)*c2 + Math.pow(BASE, 3)*c3 + Math.pow(BASE, 4)*c4);
        return hashValue % accounts.length;
    }
    
    public boolean createAccount(long accountNumber, String name, String address, double creditLimit, double balance) {
        int index = hash(accountNumber);
        if (accounts[index] != null && !deleted[index]) {
            return false;
        }
        
        if (size == accounts.length) {
            growArray();
        }

        Account account = new Account();
        account.setCreditCard(accountNumber);
        account.setCardHolder(name);
        account.setAddress(address);
        account.setCreditLimit(creditLimit);
        account.setBalOwing(balance);

        accounts[index] = account;
        deleted[index] = false;
        size++;

        return true;
    }
    
    public boolean deleteAccount(long accountNumber) {
        int index = hash(accountNumber);
        if (accounts[index] != null && !deleted[index]) {
            return false;
        }

        accounts[index] = null;
        deleted[index] = true;
        
        return true;
    }

    public boolean adjustCreditLimit(long accountNumber, double newLimit) {
    	int index = hash(accountNumber);
        if (accounts[index] != null && deleted[index]) {
            return false;
        }
        
        accounts[index].setCreditLimit(newLimit);

        return true;
    }

    public boolean makePurchase(long accountNumber, double price) throws Exception {
    	int index = hash(accountNumber);
        if (accounts[index] != null && deleted[index]) {
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

	public String getAccount(long accountNumber) {
    	int index = hash(accountNumber);
        if (accounts[index] == null || deleted[index]) {
            return null;
        }
		
	    Account account = accounts[index];
	    return account.getCreditCard() + "\n" +
	           account.getCardHolder() + "\n" +
	           account.getAddress() + "\n" +
	           account.getCreditLimit() + "\n" +
	           account.getBalOwing();
	}

}
