package project4;
import java.util.Vector;
import java.util.Scanner;

/**
 * Holds and edits all information pertaining to member records.
 * @author Zach Humphries
 *
 */
public class MemberRecord extends Record {
	private Vector<ServiceRecord> services = new Vector<ServiceRecord>();
	private String status;
	private Scanner input = new Scanner(System.in);
	/**
	 * Prompts user for member information and sets the corresponding variables.
	 */
	public MemberRecord() {
		System.out.println("Enter member name:");
		setName(input.nextLine());
		System.out.println("Enter member number:");
		setNumber(Integer.valueOf(input.nextLine()));
		System.out.println("Enter member street address:");
		setStreet(input.nextLine());
		System.out.println("Enter member city:");
		setCity(input.nextLine());
		System.out.println("Enter member state:");
		setState(input.nextLine());
		System.out.println("Enter member postal zip code:");
		setZip(Integer.valueOf(input.nextLine()));
		this.status = "Active";
	}
	/**
	 * Appends given ServiceRecord to services vector.
	 * @param sr
	 */
	public void addService(ServiceRecord sr) {
		this.services.add(sr);
	}
	/**
	 * Clears all ServiceRecords from the services vector.
	 */
	public void clearServices() {
		this.services = new Vector<ServiceRecord>();
	}
	/**
	 * Sets status in the record to the input value.
	 * @param inputStatus
	 */
	public void setStatus(String inputStatus) {
		this.status = inputStatus;
	}
	/**
	 * Retrieves the specified ServiceRecord.
	 * @param serviceNumber
	 * @return ServiceRecord
	 */
	public ServiceRecord getService(int serviceNumber) {
		return this.services.elementAt(serviceNumber);
	}
	/**
	 * Retrieves the status of the record.
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}	
	/**
	 * Retrieves the number of ServiceRecords in the services vector.
	 * @return
	 */
	public int getSize() {
		return services.size();
	}
}