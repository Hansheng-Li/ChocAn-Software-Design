package project4;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of Project4.
 * @author team5
 * @version 1.0
 *
 */
public class Program {
	private Scanner input = new Scanner(System.in);
	
	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main (String[] args) throws IOException {
		ManageRecords records = new ManageRecords();
		while (true) {
			new Program().iterate(records);
		}
	}
	
	/**
	 * Prompts user to login to specific terminal or exit program.
	 * @param records
	 * @throws IOException
	 */
	public void iterate(ManageRecords records) throws IOException {
		int user;
		System.out.println("\nSelect user type or exit.");
		System.out.println("\n\t" + "(1) Manager Terminal");
		System.out.println("\n\t" + "(2) Operator Terminal");
		System.out.println("\n\t" + "(3) Provider Terminal");
		System.out.println("\n\t" + "(4) Exit");
		user = input.nextInt();
		
		switch (user) {
		case 1:
			new ManagerTerminal(records);
			break;
		case 2:
			new OperatorTerminal(records);
			break;
		case 3:
			new ProviderTerminal(records);
			break;
		case 4:
			System.out.println("\n" + "Exiting software.");
			System.exit(0);
		default:
			System.out.println("Invalid selection.");
		}
	}
}
