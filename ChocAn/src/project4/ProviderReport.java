package project4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a summary report for a provider given a specific provider number.
 * @author Trenton Whalen
 *
 */
public class ProviderReport {
	private static DecimalFormat df = new DecimalFormat("#0.00");
	private LocalDate now = LocalDate.now();
	
	/**
	 * Prints formatted .txt provider report file.
	 * @param p
	 * @throws IOException
	 */
	public void print(ProviderRecord p) throws IOException {
		File report = new File(p.getName() + " " + DateTimeFormatter.ofPattern("MM-dd-yyyy").format(now) + ".txt");
		if(report.exists()) report.delete();
		report.createNewFile();
		FileWriter writer = new FileWriter(report);
		
		writer.append(p.getName() + '\n');
		writer.flush();
		writer.append(p.getNumber() + "" + '\n');
		writer.flush();
		writer.append(p.getStreet() + '\n');
		writer.flush();
		writer.append(p.getCity() + '\n');
		writer.flush();
		writer.append(p.getState() + '\n');
		writer.flush();
		writer.append(p.getZip() + "" + '\n' + '\n');
		writer.flush();
		
		double total = 0.0;
		int i;
		for (i = 0; i < p.size(); i++) {
			ProviderForm f = p.getForm(i);
			writer.append("  " + f.getServiceDate() + '\n');
			writer.flush();
			writer.append("  " + f.getDateTimeReceived() + '\n');
			writer.flush();
			writer.append("  " + f.getMemberName() + '\n');
			writer.flush();
			writer.append("  " + f.getMemberNumber() + "" + '\n');
			writer.flush();
			writer.append("  " + f.getServiceCode() + "" + '\n');
			writer.flush();
			writer.append("  $" + df.format(f.getServiceFee()) + '\n' + '\n');
			writer.flush();
			total += f.getServiceFee();
		}
		writer.append("Consultations: " + (i++) + "" + '\n');
		writer.flush();
		writer.append("Total Owed: $" + df.format(total) + "" + '\n');
		writer.flush();
		writer.close();
		EFTData eft = new EFTData();
		eft.print(p.getName(), p.getNumber(), total);
	}
}
