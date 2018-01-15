package project4;
import java.util.Vector;
import java.util.Scanner;

/**
 * Holds and edits all information pertaining to provider records.
 * @author Zach Humphries
 *
 */
public class ProviderRecord extends Record {
	private Vector<ProviderForm> forms;
	private Scanner input = new Scanner(System.in);
	/**
	 * Prompts user for provider information and sets the corresponding variables.
	 */
	public ProviderRecord() {
		forms = new Vector<ProviderForm>();
		System.out.println("Enter provider name:");
		setName(input.nextLine());
		System.out.println("Enter provider number:");
		setNumber(Integer.valueOf(input.nextLine()));
		System.out.println("Enter provider street address:");
		setStreet(input.nextLine());
		System.out.println("Enter provider city:");
		setCity(input.nextLine());
		System.out.println("Enter provider state:");
		setState(input.nextLine());
		System.out.println("Enter provider postal zip code:");
		setZip(Integer.valueOf(input.nextLine()));
	}
	/**
	 * Appends given ProviderForm to forms vector.
	 * @param pf
	 */
	public void addForm(ProviderForm pf) {
		this.forms.add(pf);
	}
	/**
	 * Clears all ProviderForms from the forms vector.
	 */
	public void clearForms() {
		forms = new Vector<ProviderForm>();
	}
	/**
	 * Retrieves the specified ProviderForm from the forms vector.
	 * @param formNumber
	 * @return ProviderForm
	 */
	public ProviderForm getForm(int formNumber) {
		return this.forms.elementAt(formNumber);
	}
	/**
	 * Retrieves the number of ProviderForms in the forms vector.
	 * @return
	 */
	public int size() {
		return forms.size();
	}
}