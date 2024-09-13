/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 1
Due Date: Jan. 31, 2024
*/


package mailsystempackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Message {
	
	private int messageNumber;
	private char status;
	private String sender;
	private String recipient;
	private String subject;
	private String body;
	
	public Message(int messageNumber, char status, String sender, 
			String recipient, String subject, String body) {
		this.messageNumber = messageNumber;
		this.status = status;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
	}
	
	public int getMessageNumber() {
		return messageNumber;
	}
	
	public char getStatus() {
        return status;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
    
 
    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }
    
    public void setStatus(char status) {
        this.status = status;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
   public String getTruncatedSender() {
        return (sender.length() <= 15) ? sender : sender.substring(0, 15);
    }

    public String getTruncatedSubject() {
        return (subject.length() <= 30) ? subject : subject.substring(0, 30);
    }
    
    public void printAsLine(PrintStream outputStream) {
    	 String formattedSubject = String.format("%-30s", getTruncatedSubject());

    	 outputStream.printf("%3d %c %-15s %s%n", getMessageNumber(), getStatus(), 
    			 getTruncatedSender(), formattedSubject);
    }
    
    public Message readMessageFromFile(Scanner scanner, PrintStream outputStream) {
        int messageNumber = scanner.nextInt();
        char status = scanner.next().charAt(0);
        scanner.nextLine();

        String sender = scanner.nextLine();
        String recipient = scanner.nextLine();
        String subject = scanner.nextLine();

        StringBuilder bodyBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("EOF")) {
            bodyBuilder.append(line).append("\n");
        }
        scanner.close();
        outputStream.println("Reading message #" + messageNumber + " from file... done.");

        return new Message(messageNumber, status, sender, recipient, subject, bodyBuilder.toString());
    }
   
    public void writeMessageToFile(String fileName, Message message, PrintStream stream) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(message.getMessageNumber() + " " + message.getStatus());
            writer.println(message.getSender());
            writer.println(message.getRecipient());
            writer.println(message.getSubject());
            writer.print(message.getBody());
            writer.println("EOF");
			stream.println("Writing message #" + message.getMessageNumber() + " to \"" + fileName + "\"... done.");
			writer.close();
        } catch (IOException e) {
            stream.println("Error writing message #" + message.getMessageNumber() + " to \"" + fileName + "\".");
            e.printStackTrace();
        }
    }
    
}
