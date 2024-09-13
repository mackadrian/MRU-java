package mailsystempackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MailSystem {
	private static final int MAX_MESSAGES = 500;
	private Message[] messages;
	private int messageCount;
	private int highestMessageNumber;
	
	public MailSystem() {
		this.messages = new Message[MAX_MESSAGES];
		this.messageCount = 0;
	}
	
	public int getMessageCount() {
		return messageCount;
	}
	
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	
	 public int setHighestMessageNumber(int highestMessageNumber) {
	        this.highestMessageNumber = highestMessageNumber;
	        return this.highestMessageNumber;
	    }
	
	public int getMessagesLength() {
	    return messages.length;
	}
	
	public int getNextAvailableMessageNumber() {
	     return highestMessageNumber + 1;
	     }
	
	public int getHighestMessageNumber() {
		return highestMessageNumber;
	}
	
	public Message getMessageFromList(int index) {
        if (index >= 0 && index < messageCount) {
            return messages[index];
        } else {
            return null;
        }
    }
	
	public int getUnreadMessages(String user) {
		int unreadCount = 0;
		for (int i = 0; i < getMessageCount(); i++) {
			  if (messages[i].getRecipient().equals(user) && 
					  messages[i].getStatus() == 'N') {
	                unreadCount++;
	        }
		}
	     return unreadCount;
	}
	
	public Message[] getMessagesForUser(String user) {
	    Message[] userMessages = new Message[messageCount];
	    int userHasMail = 0;

	    for (int i = 0; i < getMessageCount(); i++) {
	        if (messages[i] != null && user.equals(messages[i].getRecipient())) {
	            userMessages[userHasMail++] = messages[i];
	        }
	    }
	    // Copies array into a new array
	    Message[] result = new Message[userHasMail];
	    for (int i = 0; i < userHasMail; i++) {
	        result[i] = userMessages[i];
	    }

	    return result;
	}
	
	
    public void addMessageToMail(Message message) {
        if (getMessageCount() < getMessagesLength()) {
            int nextMessageNumber = getNextAvailableMessageNumber();
            message.setMessageNumber(nextMessageNumber);

            messages[getMessageCount()] = message;
            setMessageCount(getMessageCount() + 1);

            // Update highestMessageNumber if necessary
            if (nextMessageNumber > getHighestMessageNumber()) {
                setHighestMessageNumber(nextMessageNumber);
            }
        }
    }


	public void readMail(String fileName) {
    	try (Scanner scan = new Scanner(new File(fileName))) {
            while (scan.hasNext()) {
                int messageNumber = scan.nextInt();
                char status = scan.next().charAt(0);
                scan.nextLine();

                String sender = scan.nextLine();
                String recipient = scan.nextLine();
                String subject = scan.nextLine();
                StringBuilder bodyBuilder = new StringBuilder();

                String line;
                while (!(line = scan.nextLine()).equals("EOF")) {
                    bodyBuilder.append(line).append("\n");
                }
                String body = bodyBuilder.toString().trim();
                Message message = new Message(messageNumber, status, 
                		sender, recipient, subject, body);
                addMessageToMail(message);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

	public void writeMail(String filename) {
	    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
	        for (int i = 0; i < getMessageCount(); i++) {
	            Message message = messages[i];
	            writer.println(message.getMessageNumber() + " " + message.getStatus());

	            writer.println(message.getSender());
	            writer.println(message.getRecipient());
	            writer.println(message.getSubject());

	            // Print the body with appropriate formatting
	            String[] bodyLines = message.getBody().split("\n");
	            for (String line : bodyLines) {
	                writer.println(line);
	            }

	            writer.println("EOF");
	            writer.println();  // Separate each message block with a new line
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}