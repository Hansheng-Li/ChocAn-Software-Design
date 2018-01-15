/**
* JUnit Test for Provider Report Class. Tests success of data printed to output file.
* No sanity test was made, since all stored data is done in other coupled classes.
*
* @author Julian Diaz
* @version 1.0
*
*/

package project4.junit;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import project4.ProviderForm;
import project4.ProviderRecord;
import project4.ProviderReport;

public class ProviderReportTest {
	ByteArrayInputStream prIn, pfIn;
	ProviderReport p;
	ProviderRecord pRec;
	String now;
	/**
	 * Test set up stage. Manually sets input that would normally be entered by user to create
	 * a test Provider record and two Provider forms. These objects are then used to print an 
	 * associated provider report file and EFT data.
	 * @throws IOException 
	 */
	@Before
	public void setUp() throws IOException {
		prIn = new ByteArrayInputStream("Dr. Frank\n123456789\n300 Main St\nTuscaloosa\nAL\n33333\n".getBytes());
		System.setIn(prIn);
		pRec = new ProviderRecord();
		for (int i = 0; i < 2; i++) {
			String s = new String("12\n05\n2017\n11122233" + Integer.toString(i) + "\n666666\n5000.50\n");
			pfIn = new ByteArrayInputStream(s.getBytes());
			System.setIn(pfIn);
			pRec.addForm(new ProviderForm("Little Child " + Integer.toString(i)));
		}
		p = new ProviderReport();
		now = new String("  " + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
		p.print(pRec);
	}

	@Test
	public void successTest() throws IOException {
		FileReader file = new FileReader("Dr. Frank 12-07-2017.txt"); //change to current date
		BufferedReader scanner = new BufferedReader(file);
		String temp = scanner.readLine();
		assertEquals("Dr. Frank",temp);
		temp = scanner.readLine();
		assertEquals("123456789",temp);
		temp = scanner.readLine();
		assertEquals("300 Main St",temp);
		temp = scanner.readLine();
		assertEquals("Tuscaloosa",temp);
		temp = scanner.readLine();
		assertEquals("AL",temp);
		temp = scanner.readLine();
		assertEquals("33333",temp);
		temp = scanner.readLine();
		temp = scanner.readLine();
		assertEquals("  12-5-2017",temp);
		temp = scanner.readLine();
		assertEquals(now,temp);
		temp = scanner.readLine();
		assertEquals("  Little Child 0",temp);
		temp = scanner.readLine();
		assertEquals("  111222330",temp);
		temp = scanner.readLine();
		assertEquals("  666666",temp);
		temp = scanner.readLine();
		assertEquals("  $5000.50",temp);
		temp = scanner.readLine();
		assertEquals("",temp);
		temp = scanner.readLine();
		assertEquals("  12-5-2017",temp);
		temp = scanner.readLine();
		assertEquals(now,temp);
		temp = scanner.readLine();
		assertEquals("  Little Child 1",temp);
		temp = scanner.readLine();
		assertEquals("  111222331",temp);
		temp = scanner.readLine();
		assertEquals("  666666",temp);
		temp = scanner.readLine();
		assertEquals("  $5000.50",temp);
		temp = scanner.readLine();
		assertEquals("",temp);
		temp = scanner.readLine();
		assertEquals("Consultations: 2",temp);
		temp = scanner.readLine();
		assertEquals("Total Owed: $10001.00",temp);
		temp = scanner.readLine();
		assertNull(temp);
		scanner.close();
		file = new FileReader("Dr. Frank EFT.txt");
		scanner = new BufferedReader(file);
		temp = scanner.readLine();
		assertEquals("Dr. Frank",temp);
		temp = scanner.readLine();
		assertEquals("123456789",temp);
		temp = scanner.readLine();
		assertEquals("$10001.00",temp);
		temp = scanner.readLine();
		assertNull(temp);
		scanner.close();
	}
	
	@Test (expected = NullPointerException.class)
	public void failureTest() throws IOException {
		p.print(null);
	}
	
 }
