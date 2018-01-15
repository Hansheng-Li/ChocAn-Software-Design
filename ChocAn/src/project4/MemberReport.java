package project4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Member Report class: this class was called by manager terminal to generate member report. 
 * This can write to a txt file.
 * @author Hansheng Li
 *
 */
public class MemberReport {
	private LocalDate now = LocalDate.now();
	
	/**
	 * Member report constructor. Write all the information to the txt file.
	 * 
	 * @param MemberRecord and ManageRecords
	 * @throws IOException
	 */
	public void print(MemberRecord m, ManageRecords r) throws IOException {
		File report = new File(m.getName() + " " + DateTimeFormatter.ofPattern("MM-dd-yyyy").format(now) + ".txt");
		if(report.exists()) report.delete();
		report.createNewFile();
		FileWriter writer = new FileWriter(report);
		
		writer.append(m.getName() + '\n');
		writer.flush();
		writer.append(m.getNumber() + "" + '\n');
		writer.flush();
		writer.append(m.getStreet() + '\n');
		writer.flush();
		writer.append(m.getCity() + '\n');
		writer.flush();
		writer.append(m.getState() + '\n');
		writer.flush();
		writer.append(m.getZip() + "" + '\n' + '\n');
		writer.flush();
		

		for (int i = 0; i < m.getSize(); i++) {
			ServiceRecord s = m.getService(i);
			writer.append('\n' + s.getServiceDate() + '\n');
			writer.flush();
			writer.append(r.getProviderRecord(s.getProviderNumber()).getName() + "" + '\n');
			writer.flush();
			writer.append(s.getServiceName() + "" + '\n');
			writer.flush();
			writer.append(s.getComment() + '\n');
			writer.flush();
		}
		writer.close();
	}
}
