/**
* J units test for provider record
* test all the success test, failure test and sanity test
*
* @author Hansheng Li
* @version 1.0
*
*/

package project4.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project4.ProviderForm;
import project4.ProviderRecord;

public class ProviderRecordTest {

	ByteArrayInputStream providerRecordInput, ProviderFormInput, invalidInput;
	ProviderRecord p;
	ProviderForm pf;
	/**
	 * Try to enter all the information first by use setUp function.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		providerRecordInput = new ByteArrayInputStream("John\n123456789\n12345 Main St\nAccident\nMD\n21520\n".getBytes());
		ProviderFormInput = new ByteArrayInputStream("10\n20\n2017\n1111111\n111111\n222222\n12345 Main St\nAccident\nMD\n21520\n".getBytes());
		invalidInput = new ByteArrayInputStream("John\noops\n".getBytes());
		System.setIn(providerRecordInput);
		p = new ProviderRecord();
		System.setIn(ProviderFormInput);
		pf = new ProviderForm("John");
	}

	@Test
	public void successTest() {
		// Testing constructor and several get methods:
		assertEquals("John", p.getName());
		assertEquals(123456789, p.getNumber());
		assertEquals("12345 Main St", p.getStreet());
		assertEquals("Accident", p.getCity());
		assertEquals("MD", p.getState());
		assertEquals(21520, p.getZip());
	}
	
	@Test (expected = NumberFormatException.class)
	public void failureTest() {
		System.setIn(invalidInput);
		new ProviderRecord(); // user inputs "oops" instead of valid member number
	}
	
	@Test
	public void sanityTest() throws IOException {
		assertEquals(0, p.size()); // Vector size should initially be 0
		p.addForm(pf);
		assertEquals(pf, p.getForm(0)); // testing getForm method
		assertEquals(1, p.size()); // Vector size should increment (be 1)
		p.clearForms();
		assertEquals(0, p.size()); // Vector size should return to 0 (cleared)
	}
}
