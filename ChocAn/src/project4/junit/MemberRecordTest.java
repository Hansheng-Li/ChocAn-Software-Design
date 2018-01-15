package project4.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project4.ManageRecords;
import project4.MemberRecord;
import project4.ServiceRecord;

/**
 * JUnit Test: MemberRecord
 * @author tdwhalen
 *
 */
public class MemberRecordTest {

	ByteArrayInputStream memberRecordInput, serviceRecordInput, invalidInput;
	ManageRecords r;
	MemberRecord m;
	ServiceRecord s;
	
	@Before
	/**
	 * Simulates user input to initialize variables for JUnit testing.
	 * @throws IOException
	 */
	public void setUp() throws IOException {
		memberRecordInput = new ByteArrayInputStream("John\n123456789\n12345 Main St\nAccident\nMD\n21520\n".getBytes());
		serviceRecordInput = new ByteArrayInputStream("12-07-2017 01:00:00\n12-07-2017\n987654321\n123456789\n666666\ny\nbleep bloop\n".getBytes());
		invalidInput = new ByteArrayInputStream("John\noops\n".getBytes());
		System.setIn(memberRecordInput);
		m = new MemberRecord();
		r = new ManageRecords();
		r.add(m);
		System.setIn(serviceRecordInput);
		s = new ServiceRecord(r);
	}

	@Test
	/**
	 * Tests constructor method and several get methods.
	 */
	public void successTest() {
		assertEquals("John", m.getName());
		assertEquals(123456789, m.getNumber());
		assertEquals("12345 Main St", m.getStreet());
		assertEquals("Accident", m.getCity());
		assertEquals("MD", m.getState());
		assertEquals(21520, m.getZip());
		assertEquals("Active", m.getStatus());
		assertEquals(0, m.getSize());
	}
	
	@Test (expected = NumberFormatException.class)
	/**
	 * User inputs "oops" instead of valid member number.
	 */
	public void failureTest() {
		System.setIn(invalidInput);
		new MemberRecord();
	}
	
	@Test
	/**
	 * List of service records is initially zero. Service record is added, and then removed.
	 * @throws IOException
	 */
	public void sanityTest() throws IOException {
		assertEquals(0, m.getSize());
		m.addService(s);
		assertEquals(s, m.getService(0)); // testing getService method
		assertEquals(1, m.getSize());
		m.clearServices();
		assertEquals(0, m.getSize());
	}
}
