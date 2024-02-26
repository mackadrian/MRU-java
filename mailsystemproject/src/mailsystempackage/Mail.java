/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 1
Due Date: Jan. 31, 2024
*/



package mailsystempackage;

import java.util.Scanner;

public class Mail {
	public static final String MAIL_FILE = "mail.txt";
	
	public static Scanner keyboard = new Scanner (System.in);
	
	MailSystem system = new MailSystem ();

	public static void main (String [] args)
	{
		Mail program = new Mail ();
		
		program.run ();
	}
	
	public void run () {
		System.out.println ("Welcome to the Secure Corp. messaging system.");
		System.out.println ();

		System.out.print ("Reading mail from \"" + MAIL_FILE + "\"...");
		system.readMail (MAIL_FILE);
		System.out.println (" done.");
		System.out.println ();
	
		String user = switchUser ();
	
		char choice = getValidChoice ();
		while (choice != 'q'& choice != 'Q')
		{
			switch (choice)
			{
				case 'l':
				case 'L':
					listMessages (user);
					break;
				case 'v':
				case 'V':
					viewMessage (user);
					break;
				case 'c':
				case 'C':
					composeMessage (user);
					break;
				case 's':
				case 'S':
					user = switchUser ();
					break;
			}
		
			choice = getValidChoice ();
		}
	
		System.out.println ("Thanks for using the Secure Corp. messaging system.");
		System.out.println ();

		System.out.print ("Writing mail to \"" + MAIL_FILE + "\"...");
		system.writeMail (MAIL_FILE);
		System.out.println (" done.");
		System.out.println ();
	}

	char getValidChoice ()
	{
		char choice = getChoice();
		boolean isValid = acceptable(choice);
		
		if (isValid) {
			return choice;
		} else {
			System.out.println("Invalid choice, please try again.");
			return '0';
		}
	}

	char getChoice ()
	{
		System.out.println ("Available Options"); 
		System.out.println ("  L - List your messages");
		System.out.println ("  V - View a message");
		System.out.println ("  C - Compose a message");
		System.out.println ("  S - Switch users");
		System.out.println ("  Q - Quit");
		System.out.println ();
		System.out.print ("Please enter your choice: ");
	
		char choice;
		choice = keyboard.next ().charAt (0);
		// clear out newline in case next input is full line.
		keyboard.nextLine ();
	
		return choice;
	}

	boolean acceptable (char choice)
	{
		boolean flag = false;
		switch (Character.toLowerCase(choice))
		{
			case 'l':
			case 'v':
			case 'c':
			case 's':
			case 'q':
				flag = true;
				break;
		}
		return flag;
	}

	void listMessages (String user)
	{
		Message[] messages;
		messages = system.getMessagesForUser(user);
	
		System.out.println ("### S From            Subject");
		System.out.println ("----------------------------------");
		if (messages != null) {
	        for (int i = messages.length - 1; i >= 0; i--) {
	            if (messages[i] != null) {
	                messages[i].printAsLine(System.out);
	            }
	        }
	    }
		System.out.println ();
	}

	void viewMessage(String user) {
	    Message[] messages = system.getMessagesForUser(user);

	    if (messages == null || messages.length == 0) {
	        System.out.println("No messages available for the user.");
	        return;
	    }

	    System.out.print("Which message would you like to view: ");
	    int messageNumber = keyboard.nextInt();

	    boolean messageFound = false;

	    for (int i = 0; i < messages.length; i++) {
	        if (messages[i] != null && messages[i].getMessageNumber() == messageNumber) {
	            messageFound = true;

	            // display the chosen message.
	            System.out.println("Message # " + messages[i].getMessageNumber() +
	                    " (" + (messages[i].getStatus() == 'N' ? "New" : "Read") + ")");
	            System.out.println("From: " + messages[i].getSender());
	            System.out.println("Subject: " + messages[i].getSubject());
	            System.out.println("----------------------------------");
	            System.out.println(messages[i].getBody());
	            System.out.println("----------------------------------");
	            
	            messages[i].setStatus('R');
	            break;
	        }
	    }
	    if (!messageFound) {
	        System.out.println("I'm sorry; message #" + messageNumber +
	                " is not addressed to you or does not exist.");
	    }
	}
	
	
	void composeMessage(String sender) {
	    System.out.println("Recipient: ");
	    String recipient = keyboard.next();

	    keyboard.nextLine();
	    
	    System.out.println("Subject: ");
	    String subject = keyboard.nextLine();

	    System.out.println("Body (enter EOF to end body): ");
	    StringBuilder bodyBuilder = new StringBuilder();
	    String line;

	    while (!(line = keyboard.nextLine()).equalsIgnoreCase("EOF")) {
	        bodyBuilder.append(line).append("\n");
	    }

	    int nextMessageNumber = system.getNextAvailableMessageNumber() + 1;

	    // create a new message and add it to the system
	    Message newMessage = new Message(nextMessageNumber, 'N', sender, recipient, subject, bodyBuilder.toString());
	    // add the new message to the system
	    system.addMessageToMail(newMessage);

	    System.out.println("Message #" + newMessage.getMessageNumber() + " sent to " + recipient + ".");
	}

	String switchUser ()
	{
		System.out.println("Enter your name (be honest): ");
		String userName = keyboard.next();
		System.out.println("Hello " + userName + " you have " + 
				system.getUnreadMessages(userName) + " unread message(s).");
		return userName;
	}
}