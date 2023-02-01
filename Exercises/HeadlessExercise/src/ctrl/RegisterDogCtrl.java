package ctrl;

import model.Dog;
import model.Member;

public class RegisterDogCtrl {
	private Member currMember;
	
	public Member findByEmail(String email) {
		MemberCtrl mCtrl = new MemberCtrl();
		currMember = mCtrl.findByEmail(email);
		return currMember;
	}

	public Dog registerDog(String dogName, int feeYear) {
		Dog dog = new Dog(dogName, feeYear);
		currMember.addDog(dog);
		return dog;
	}

	public void updateDog(String oldName, String newName, int year) {
		Dog dog = currMember.findDogByName(oldName);
		dog.setName(newName);
		dog.setFeeYear(year);
	}


}
