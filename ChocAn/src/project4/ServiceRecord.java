package project4;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Service Records class: use for provider terminal to record services that provide to the member and 
 * and easily let manager to generate report.
 * @author Hansheng Li
 * @author Julian Diaz
 *
 */
public class ServiceRecord {
	
	private String serviceName;
	private String serviceDate;
	private String dateTimeReceived;
	private String serviceFee;
	private int providerNumber;
	private int memberNumber;
	private int serviceCode;
	private String comment;
	private Scanner input = new Scanner(System.in);
	
	public ServiceRecord(ManageRecords records) throws IOException {
		System.out.println("Enter Current date and time (MM-DD-YYYY HH:MM:SS):");
		this.serviceDate = input.nextLine();
		System.out.println("Enter Date service was provided (MM-DD-YYYY):");
		this.dateTimeReceived = input.nextLine();
		System.out.println("Enter Provider number:");
		this.providerNumber = Integer.valueOf(input.nextLine());
		System.out.println("Enter Member number:");
		this.memberNumber = Integer.valueOf(input.nextLine());
		while(verifyMember(this.memberNumber,records) != ("Verified")) {
			System.out.println("Member Number is Invalid, please enter again.");
			this.memberNumber = Integer.valueOf(input.nextLine());
		}
		System.out.println("Enter Service code:");
		this.serviceCode = Integer.valueOf(input.nextLine());
		this.serviceName = lookUpCode(serviceCode);
		System.out.println("Enter Comments (100 characters):");
		this.comment = input.nextLine();	
		System.out.println("Service fee was: " +this.serviceFee + "\n");
	}
	
	private boolean isInt(String input) {
		try {
			Integer.parseInt(input);
		}
		catch(NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	/**
	 * The lookUpCode function that help to find service code and let provider to check 
	 * the service code entered was correct or wrong, if the service code entered was nonexistent
	 * it will give an error and let user to enter again.
	 * 
	 * @param sCode
	 * @return
	 * @throws IOException
	 */
	private String lookUpCode(int sCode) throws IOException {
		FileReader file = new FileReader("provider directory.txt");
		BufferedReader scanner = new BufferedReader(file);
		String temp = scanner.readLine();
		while (temp != null) {
			if (isInt(temp)) {
				if (Integer.valueOf(temp) == sCode) {
					this.serviceName = scanner.readLine();
					this.serviceFee = scanner.readLine();
					scanner.close();
					file.close();
					System.out.println("Do you mean service : " + this.serviceName +"? [y/n]");
					String test;
					test = input.nextLine();
					if (test.equals("n")) {
						System.out.println("Enter Service code:");
						this.serviceCode = Integer.valueOf(input.nextLine());
						this.serviceName = lookUpCode(serviceCode);
					}
					return this.serviceName;
				}
			}
			temp = scanner.readLine();
		}
		scanner.close();
		file.close();
		displayErrorMessage();		
		System.out.println("Enter Service code:");
		this.serviceCode = Integer.valueOf(input.nextLine());
		this.serviceName = lookUpCode(serviceCode);
		return null;
	}
	
	/**
	 * This function just help to verify the member number entered was correct or not.
	 * 
	 * @param number
	 * @param records
	 * @return
	 */
	public String verifyMember(int number, ManageRecords records) {
		//verify the member is real/active in database
		
		if(records.getMemberRecord(number) == null)
			return ("Invalid Number");
		if(records.getMemberRecord(number).getStatus() == "Suspended")
			return ("Suspended");
		else
			return ("Verified");
	}
	
	public String getServiceDate() {
		return this.serviceDate;
	}
	public String getDateTimeReceived() {
		return this.dateTimeReceived;
	}
	public int getProviderNumber() {
		return this.providerNumber;
	}
	public int getMemberNumber() {
		return this.memberNumber;
	}
	public String getServiceName() {
		return this.serviceName;
	}
	public String getServiceFee() {
		return this.serviceFee;
	}
	public String getComment() {
		return this.comment;
	}
	public void displayErrorMessage() {
		System.out.println("ERROR: Nonexistent service code was entered.\n");
	}

}


