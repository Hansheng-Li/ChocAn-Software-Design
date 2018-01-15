/**
 * Junit test for the ManagerTerminal class. Tests for a successful
 * printing of a provider record given the proper input and a failure
 * if the user gives an invalid input.
 * 
 * @author Zach Humphries
 * @version 1.0
 * 
 */
package project4.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

import project4.ManageRecords;
import project4.ManagerTerminal;
import project4.ProviderRecord;

public class ManagerTerminalTest {
	private ProviderRecord provRecord;
	private ByteArrayInputStream ProviderRecordInput, managerTerminalInstructions, invalidInput;
	private ManageRecords records;
	private LocalDate now = LocalDate.now();
	
	@Before
	public void setUp() throws Exception {
		ProviderRecordInput = new ByteArrayInputStream("John\n123456789\n300 Main St\nTuscaloosa\nAL\n33333\n".getBytes());
		managerTerminalInstructions = new ByteArrayInputStream("2\n2\n123456789\n".getBytes());
		invalidInput = new ByteArrayInputStream("oops\n".getBytes());
		System.setIn(ProviderRecordInput);
		provRecord = new ProviderRecord();
		records = new ManageRecords();
		records.add(provRecord);
		System.setIn(managerTerminalInstructions);
		new ManagerTerminal(records);
	}

	@Test
	public void successTest() throws IOException {
		//checks that a ProviderRecord was correctly constructed and printed given the correct user input in the terminal
		File report = new File(provRecord.getName() + " " + DateTimeFormatter.ofPattern("MM-dd-yyyy").format(now) + ".txt");
		assertTrue(report.exists());
	}
	

	@Test (expected = InputMismatchException.class)
	public void ioFailureTest() throws IOException {
		System.setIn(invalidInput);
		new ManagerTerminal(records);
	}
}
