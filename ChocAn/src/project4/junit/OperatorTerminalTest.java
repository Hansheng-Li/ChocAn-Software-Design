package project4.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project4.ManageRecords;
import project4.OperatorTerminal;
import project4.ProviderRecord;

public class OperatorTerminalTest {
	ProviderRecord provRecord;
	ProviderRecord returnedRecord;
	ByteArrayInputStream providerRecordInput, operatorTerminalInput, invalidInput, sanityTestInput;
	ManageRecords records;
	
	
	@Before
	public void setUp() throws Exception {
	//	providerRecordInput = new ByteArrayInputStream("John\n123456789\n300 Main St\nTuscaloosa\nAL\n33333\n".getBytes());
		operatorTerminalInput = new ByteArrayInputStream("2\n1\nJohn\n123456789\n300 Main St\nTuscaloosa\nAL\n33333\n".getBytes());
		invalidInput = new ByteArrayInputStream("oops\n".getBytes());
		sanityTestInput = new ByteArrayInputStream("2\3\123456789".getBytes());
		provRecord = new ProviderRecord();
		records = new ManageRecords();
		//records.add(provRecord);
		System.setIn(providerRecordInput);
		new OperatorTerminal(records); //adds a new provider record of john
	}

	@Test
	public void successTest() {
		//check that a ProviderRecord was correctly constructed and printed given the correct input
		assertEquals("John", returnedRecord.getName());
		assertEquals(123456789, returnedRecord.getNumber());
		assertEquals("300 Main St", returnedRecord.getStreet());
		assertEquals("Tuscaloosa", returnedRecord.getCity());
		assertEquals("AL", returnedRecord.getState());
		assertEquals(33333, returnedRecord.getZip());
	}
	
	@Test (expected = NumberFormatException.class)
	public void failureTest() throws IOException {
		System.setIn(invalidInput);
		new OperatorTerminal(records); // user inputs "oops" instead of valid option in the menu
	}
	
	@Test 
	public void sanityTest() throws IOException {
		System.setIn(sanityTestInput);
		new OperatorTerminal(records);
		assertEquals(0, records.getSizePR());
	}
}
