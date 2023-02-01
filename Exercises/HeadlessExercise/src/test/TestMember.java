package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Dog;
import model.Member;

class TestMember {
	private Member member;
	private Dog dog;
	private static final String NAME = "Joe";
	private static final String EMAIL = "joe@joe";
	private static final String PHONE = "12121212";
	private static final int FEEYEAR = 2022;
	private static final String DOGNAME = "Fido";
	
	@BeforeEach
	void setUp()  {
		member = new Member(NAME, EMAIL, PHONE);
		dog = new Dog(DOGNAME, FEEYEAR);
	}

	@Test
	void testGetDogsEncapsulation() {
		member.addDog(dog);
		ArrayList<Dog> dogs = member.getDogs();
		assertNotSame(member.getDogs(), dogs);
	}
	
	@Test
	public void testFeeDueNoDog() {
		boolean feeDue = member.isFeeDue(FEEYEAR);
		assertFalse(feeDue);
	}
	
	@Test
	public void testFeeDueOneDogISDUE() {
		member.addDog(dog);
		boolean feeDue = member.isFeeDue(FEEYEAR + 1);
		assertTrue(feeDue);
	}
	
	@Test
	public void testFeeDueOneDogISNOTDUE() {
		member.addDog(dog);
		boolean feeDue = member.isFeeDue(FEEYEAR);
		boolean feeDue2 = member.isFeeDue(FEEYEAR - 5);
		assertFalse(feeDue);
		assertFalse(feeDue2);
	}
	
	@Test
	public void testFeeDue3DogsISDUE() {
		member.addDog(dog);
		member.addDog(new Dog("Rex", 1999));
		member.addDog(new Dog("Rolex", FEEYEAR));
		boolean feeDue = member.isFeeDue(FEEYEAR);
		assertTrue(feeDue);
	}
	
	@Test
	public void testKnownOffendersOK() {
		ArrayList<Dog> ko = new ArrayList<>();
		member.addDog(dog);
		Dog d1 = new Dog("Rex", 2019);
		Dog d2 = new Dog("Rolex", FEEYEAR);
		Dog d3 = new Dog("Spot", 1999);
		ko.add(d1);
		ko.add(d3);
		member.addDog(d1);
		member.addDog(d2);
		member.addDog(d3);
		
		ArrayList<Dog> res = member.unpaidList(FEEYEAR);

		assertEquals(ko.size(), res.size());
		for(Dog d : ko) {
			assertTrue(res.contains(d));
		}
	}
}











