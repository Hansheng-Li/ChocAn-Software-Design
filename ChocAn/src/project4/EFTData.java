package project4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Creates a summary of EFT data for a specific provider. Called after creating a provider report.
 * @author Trenton Whalen
 *
 */
public class EFTData {
	private static DecimalFormat df = new DecimalFormat("#0.00");
	
	/**
	 * Prints formatted .txt EFT data file.
	 * @param name
	 * @param number
	 * @param amount
	 * @throws IOException
	 */
	public void print(String name, int number, double amount) throws IOException {
		File report = new File(name + " EFT.txt");
		if(report.exists()) report.delete();
		report.createNewFile();
		FileWriter writer = new FileWriter(report);
		
		writer.append(name + '\n');
		writer.flush();
		writer.append(number + "" + '\n');
		writer.flush();
		writer.append("$" + df.format(amount));
		writer.flush();
		writer.close();
	}
}
