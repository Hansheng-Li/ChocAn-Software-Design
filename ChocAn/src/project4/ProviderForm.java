package project4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Form completed by the Provider to verify service record.
 * @author Trenton Whalen
 *
 */
public class ProviderForm {
	private String serviceDate = new String();
	private String dateTimeReceived = new String();
	private int memberNumber = 0;
	private String memberName = new String();
	private int serviceCode = 0;
	private double serviceFee = 0;
	private Scanner input = new Scanner(System.in);
	
	/**
	 * Prompts provider to enter necessary information for the form.
	 * @param memberName
	 */
	public ProviderForm(String memberName) {
		System.out.println("\nPROVIDER FORM");
		this.memberName = memberName;
		System.out.println("\nEnter month of service: ");
		this.serviceDate += (input.nextInt() + "-");
		System.out.println("Enter day of service: ");
		this.serviceDate += (input.nextInt() + "-");
		System.out.println("Enter year of service: ");
		this.serviceDate += input.nextInt();
		System.out.println("Enter Member Number: ");
		this.memberNumber = input.nextInt();
		System.out.println("Enter Service Code: ");
		this.serviceCode = input.nextInt();
		System.out.println("Enter Service Fee: ");
		this.serviceFee = input.nextDouble();
		this.dateTimeReceived = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Returns service date entered by provider.
	 * @return
	 */
	public String getServiceDate() {
		return this.serviceDate;
	}
	
	/**
	 * Returns date-time form was received by computer.
	 * @return
	 */
	public String getDateTimeReceived() {
		return this.dateTimeReceived;
	}
	
	/**
	 * Returns member number.
	 * @return
	 */
	public int getMemberNumber() {
		return this.memberNumber;
	}
	
	/**
	 * Returns member name.
	 * @return
	 */
	public String getMemberName() {
		return this.memberName;
	}
	
	/**
	 * Returns service code.
	 * @return
	 */
	public int getServiceCode() {
		return this.serviceCode;
	}
	
	/**
	 * Returns seervice fee.
	 * @return
	 */
	public double getServiceFee() {
		return this.serviceFee;
	}
}
