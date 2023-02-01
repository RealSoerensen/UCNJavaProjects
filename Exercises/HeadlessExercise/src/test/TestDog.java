package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Dog;

class TestDog {
	public static final int FEEYEAR = 2022;
	public static final String NAME = "Fido";
	private Dog dog;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeAll");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("AfterAll");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("  setUp (BeforeEach)");
		this.dog = new Dog(NAME, FEEYEAR);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("  tearDown (AfterEach)");
	}

	@Test
	void testCreate2Param() {
		System.out.println("    Test 1");
		// Arrange (done)
		// Act (done)
		// Assert
		assertEquals(NAME, dog.getName());
		assertEquals(FEEYEAR, dog.getFeeYear());
	}
	
	@Test
	public void testFeeDueOK() {
		// Arrange
		// Act
		boolean feeDue = dog.isFeeDue(FEEYEAR);
		// Assert
		assertFalse(feeDue);
	}
	
	@Test
	public void testFeeDueBeforeCurr() {
		//Arrange
		int feeYear1 = FEEYEAR - 2;
		int feeYear2 = FEEYEAR - 123;
		//Act
		boolean feeDue1 = dog.isFeeDue(feeYear1);
		boolean feeDue2 = dog.isFeeDue(feeYear2);
		//Assert
		assertFalse(feeDue1);
		assertFalse(feeDue2);
	}

}
