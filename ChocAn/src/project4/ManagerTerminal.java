package project4;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Manager Terminal class. A boundary Class to facilitate Manager Terminal interface. 
 * Supports running Main Accounting Procedure and printing individual member/provider reports.
 * @author Julian Diaz
 *
 */
public class ManagerTerminal {
    private int selection;
	private Scanner input = new Scanner(System.in);
	private int memberNum;
	private int providerNum;

	/**
	 * Manager Terminal constructor. Outputs series of menus for printing reports.
	 * Needs to be passed the master ManageRecords object containing all records currently
	 * in the system.
	 * @param records
	 * @throws IOException
	 */
    public ManagerTerminal(ManageRecords records) throws IOException {
        System.out.println("Select Option:");
        	System.out.println("\n\t (1) Run Main Accounting Procedure"); 
        	System.out.println("\n\t (2) Print individual report");
        	this.selection = input.nextInt();
        	boolean v = true;
        switch (selection) {
    			case 1:
    				PrintReport(this.selection,0,records);
    				break;
    			case 2:
    				System.out.println("Select Report Type:");
    				System.out.println("\n\t (1) Member Report");
    				System.out.println("\n\t (2) Provider Report");
    	            this.selection = input.nextInt();
    	            switch (selection) {
    	            		case 1:
    	            			System.out.println("Please enter member number:");
    	            			this.memberNum = input.nextInt();
    	            			v = PrintReport(this.selection,this.memberNum,records);
    	            			break;
    	            		case 2:
    	            			System.out.println("Please enter provider number:");
    	            			this.providerNum = input.nextInt();
    	            			v = PrintReport(this.selection,this.providerNum,records);
    	            			break;
    	            		default:
        	            		System.out.println("Invalid entry. Aborting...");
        	            		v = false;
        	            		break;
    	            }
    	            break;
    			default:
    	            	System.out.println("Invalid entry. Aborting...");
    	            	v = false;
    	            	break;
        }
    	    if (v) System.out.println("Please check file system for requested report(s).");
    }

    /**
     * Print Report function. Can process reports for all existing member and provider data
     * or process an individually specified report.
     * @param s <-- selection
     * @param n <-- ID number
     * @param records
     * @throws IOException
     */
	private boolean PrintReport(int s, int n, ManageRecords records) throws IOException {
		if (n == 0) {
    			ProviderReport pReport = new ProviderReport();
    			int i;
    			for (i = 0; i < records.getSizePR(); i++) {
    				pReport.print(records.getProviderRecordByIndex(i));
    				pReport = new ProviderReport();
    			}
    			MemberReport mReport = new MemberReport();
    			for (i = 0; i < records.getSizeMR(); i++) {
    				mReport.print(records.getMemberRecordByIndex(i),records);
    				mReport = new MemberReport();
    			}
    			return true;
		}
		if (s == 1) {
    			MemberReport mReport = new MemberReport();
    			if (records.getMemberRecord(n) == null) return false;
    			mReport.print(records.getMemberRecord(n),records);
    			return true;
		}
		if (s == 2) {
    			ProviderReport pReport = new ProviderReport();
    			if (records.getProviderRecord(n) == null) return false;
    			pReport.print(records.getProviderRecord(n));
    			return true;
    		}
		return false;
	}
}
