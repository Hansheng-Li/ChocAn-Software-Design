package project4;

import java.io.IOException;
import java.util.Scanner;

/**
 * Boundary class with which the Provider interacts to add service records and provider forms. 
 * @author Michael Hall
 *
 */
public class ProviderTerminal {
	private int memberNumber;
	private int providerNumber;
	private String memberName;
	private Scanner input = new Scanner(System.in);
	//make a constructor that calls the three functions below
	//constructor acts as the main for this class
	//the manage records class needs to be passed to the constructor so I can see and edit the data
	
	//when invoking providerform, pass in the member name that is associated with the service
	/**
	 * Provider terminal main function. Calls the other functions in the class.
	 * Needs to be passed the ManageRecords object.
	 * @param records
	 * @throws IOException
	 */
	public ProviderTerminal (ManageRecords records) throws IOException {
		System.out.println("Please enter member number: ");
		memberNumber = input.nextInt();
		if(verifyMember(memberNumber,records) == ("Invalid Number"))
			return;
		else if(verifyMember(memberNumber,records) == ("Suspended")) {
			System.out.println("User is currently Suspended");
			return;
		}
		else if(verifyMember(memberNumber,records) == ("Verified"))
			System.out.println("Member Verified");
		addServiceRecord(records, memberNumber);
		addProviderForm(records,memberNumber);
	}

	/**
	 * Verifies that the member number provider is a valid number 
	 * that applies to an active member.
	 * Needs to be passed the member number and the ManageRecords object.
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
	
	/**
	 * Adds the service record to the members service record.
	 * Needs to be passed the ManageRecords and memberNumber.
	 * @param records
	 * @param memberNumber
	 * @throws IOException
	 */
	
	public void addServiceRecord(ManageRecords records, int memberNumber) throws IOException {
		ServiceRecord newRecord = new ServiceRecord(records);
		//should call the service record constructor which
		//will populate with data
		records.getMemberRecord(memberNumber).addService(newRecord);
		//add the created service record to the member record
	}
	
	/**
	 * Adds a provider form the list of provider forms for the provider.
	 * Needs to be passed the ManageRecords object and memberNumber
	 * @param records
	 * @param memberNumber
	 */
	
	public void addProviderForm(ManageRecords records, int memberNumber) {
		memberName = records.getMemberRecord(memberNumber).getName();
		ProviderForm p = new ProviderForm(memberName);
		//this will call the provider form constructor which
		//will take information from provider and populate form
		System.out.println("Please enter your provider number:");
		providerNumber = input.nextInt();
		records.getProviderRecord(providerNumber).addForm(p);
		//add the created form to the list of provider records
	}
}