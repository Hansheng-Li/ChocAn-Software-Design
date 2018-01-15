package project4;

import java.util.Vector;

/**
 * Controller class containing all member and provider records.
 * @author Trenton Whalen
 * @author Julian Diaz
 *
 */
public class ManageRecords {
	private Vector<MemberRecord> memberRecords = new Vector<MemberRecord>();
	private Vector<ProviderRecord> providerRecords = new Vector<ProviderRecord>();
	
	/**
	 * Overloaded function. Add a member record.
	 * @param record
	 */
	public void add(MemberRecord record) {
		this.memberRecords.add(record);
	}
	
	/**
	 * Overloaded function. Add a provider record.
	 * @param record
	 */
	public void add(ProviderRecord record) {
		this.providerRecords.add(record);
	}
	
	/**
	 * Delete a member given their member number.
	 * @param number
	 */
	public void deleteMember(int number) {
		int index = searchMember(number);
		if(index == -1) {
			System.out.println("Member Number does not exist in records.");
			return;
		}
		this.memberRecords.remove(index);
	}
	
	/**
	 * Delete a provider given their provider number.
	 * @param number
	 */
	public void deleteProvider(int number) {
		int index = searchProvider(number);
		if(index == -1) {
			System.out.println("Provider Number does not exist in records.");
			return;
		}
		this.providerRecords.remove(index);
	}
	
	/**
	 * Returns member record corresponding to member number. Returns null if member number does not exist.
	 * @param number
	 * @return
	 */
	public MemberRecord getMemberRecord(int number) {
		int index = searchMember(number);
		if(index == -1) {
			System.out.println("Member Number does not exist in records.");
			return null;
		}
		return this.memberRecords.get(index);
	}
	
	/**
	 * Returns provider record corresponding to provider number. Returns null if provider number does not exist.
	 * @param number
	 * @return
	 */
	public ProviderRecord getProviderRecord(int number) {
		int index = searchProvider(number);
		if(index == -1) {
			System.out.println("Provider Number does not exist in records.");
			return null;
		}
		return this.providerRecords.get(index);
	}
	
	/**
	 * Returns member record by index in memberRecords Vector. Necessary for MAP.
	 * @param index
	 * @return
	 */
	public MemberRecord getMemberRecordByIndex(int index) {
		return this.memberRecords.get(index);
	}
	
	/**
	 * Returns provider record by index in providerRecords Vector. Necessary for MAP.
	 * @param index
	 * @return
	 */
	public ProviderRecord getProviderRecordByIndex(int index) {
		return this.providerRecords.get(index);
	}
	
	/**
	 * Returns size of memberRecords Vector. Necessary for MAP.
	 * @return
	 */
	public int getSizeMR()  {
		return this.memberRecords.size();
	}
	
	/**
	 * Returns size of providerRecords Vector. Necessary for MAP.
	 * @return
	 */
	public int getSizePR()  {                  
		return this.providerRecords.size();
	}
	
	/**
	 * Linear search of member records given a member number. Returns index in memberRecords Vector or -1 if number does not exist.
	 * @param number
	 * @return
	 */
	private int searchMember(int number) {
		for(int i = 0; i < this.memberRecords.size(); i++) {
			if(number == this.memberRecords.get(i).getNumber()) return i;
		}
		return -1;
	}
	
	/** 
	 * Linear search of provider records given a provider number. Returns index in providerRecords Vector or -1 if number does not exist.
	 * @param number
	 * @return
	 */
	private int searchProvider(int number) {
		for(int i = 0; i < this.providerRecords.size(); i++) {
			if(number == this.providerRecords.get(i).getNumber()) return i;
		}
		return -1;
	}
}
