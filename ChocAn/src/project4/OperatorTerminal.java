package project4;

import java.io.IOException;
import java.util.Scanner;

/**
 * Boundary class with which the Operator interacts to add, edit, and delete records.
 * @author Trenton Whalen
 *
 */
public class OperatorTerminal {
	private Scanner input = new Scanner(System.in);
	
	/**
	 * Constructor method. Generates menu for user to perform action on records.
	 * @param records
	 * @throws IOException
	 */
	public OperatorTerminal(ManageRecords records) throws IOException {
		System.out.println("\nSelect record type.");
		System.out.println("\n\t" + "(1) Member");
		System.out.println("\n\t" + "(2) Provider");
		System.out.println("\n\t" + "(3) Exit");
		int selection = input.nextInt();
		String type = new String();
		switch (selection) {
		case 1:
			type = "Member";
			break;
		case 2:
			type = "Provider";
			break;
		case 3:
			return;
		default:
			System.out.println("Invalid selection.");
		}
		System.out.println("\nSelect action.");
		System.out.println("\n\t" + "(1) Add " + type);
		System.out.println("\n\t" + "(2) Edit " + type);
		System.out.println("\n\t" + "(3) Delete " + type);
		System.out.println("\n\t" + "(4) Exit");
		int number = 0;
		selection = input.nextInt();
		switch (selection) {
		case 1:
			add(type, records);
			break;
		case 2:
			System.out.println("Enter " + type + " Number:");
			number = input.nextInt();
			edit(type, number, records);
			break;
		case 3:
			System.out.println("Enter " + type + " Number:");
			number = input.nextInt();
			delete(type, number, records);
			break;
		case 4:
			return;
		default:
			System.out.println("Invalid selection.");
		}
	}
	
	/**
	 * Adds record to records. Record type is specified in type parameter.
	 * @param type
	 * @param records
	 */
	private void add(String type, ManageRecords records) {
		switch (type) {
		case "Member":
			MemberRecord newMember = new MemberRecord();
			records.add(newMember);
			break;
		case "Provider":
			ProviderRecord newProvider = new ProviderRecord();
			records.add(newProvider);
			break;
		}
	}
	
	/**
	 * Generates menu for user to edit a specific aspect of a record in records. Record type is specified in the type parameter. The number parameter corresponds to the specific member/provider number.
	 * @param type
	 * @param number
	 * @param records
	 */
	private void edit(String type, int number, ManageRecords records) {
		System.out.println("\nSelect attribute to edit.");
		System.out.println("\n\t" + "(1) Name");
		System.out.println("\n\t" + "(2) Address");
		int selection = 0;
		switch (type) {
		case "Member":
			System.out.println("\n\t" + "(3) Status");
			System.out.println("\n\t" + "(4) Exit");
			selection = input.nextInt();
			MemberRecord updatedMember = records.getMemberRecord(number);
			switch (selection) {
			case 1:
				System.out.println("Enter new name.");
				updatedMember.setName(input.nextLine());
				break;
			case 2:
				System.out.println("Enter new street.");
				updatedMember.setStreet(input.nextLine());
				System.out.println("Enter new city.");
				updatedMember.setCity(input.nextLine());
				System.out.println("Enter new state.");
				updatedMember.setState(input.nextLine());
				System.out.println("Enter new ZIP.");
				updatedMember.setZip(input.nextInt());
				break;
			case 3:
				System.out.println("Enter new status.");
				updatedMember.setStatus(input.nextLine());
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid selection.");
			}
			records.deleteMember(number);
			records.add(updatedMember);
			break;
		case "Provider":
			System.out.println("\n\t" + "(3) Exit");
			selection = input.nextInt();
			ProviderRecord updatedProvider = records.getProviderRecord(number);
			switch (selection) {
			case 1:
				System.out.println("Enter new name.");
				updatedProvider.setName(input.nextLine());
				break;
			case 2:
				System.out.println("Enter new street.");
				updatedProvider.setStreet(input.nextLine());
				System.out.println("Enter new city.");
				updatedProvider.setCity(input.nextLine());
				System.out.println("Enter new state.");
				updatedProvider.setState(input.nextLine());
				System.out.println("Enter new ZIP.");
				updatedProvider.setZip(input.nextInt());
			case 3:
				return;
			default:
				System.out.println("Invalid selection.");
			}
			records.deleteMember(number);
			records.add(updatedProvider);
		}
	}
	
	/**
	 * Deletes record from records. The record type is specified by the type parameter. The number parameter corresponds to the specific member/provider number.
	 * @param type
	 * @param number
	 * @param records
	 */
	private void delete(String type, int number, ManageRecords records) {
		switch (type) {
		case "Member":
			records.deleteMember(number);
			break;
		case "Provider":
			records.deleteProvider(number);
			break;
		}
	}
}
