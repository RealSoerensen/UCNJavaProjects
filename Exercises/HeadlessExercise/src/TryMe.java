
import java.time.LocalDate;

import ctrl.MemberCtrl;
import ctrl.RegisterDogCtrl;
//import gui.DogView;
import model.Dog;
import model.Member;

public class TryMe {
	private static RegisterDogCtrl rdc = new RegisterDogCtrl();
	private static MemberCtrl mc = new MemberCtrl();
	public static void main(String[] args) {
		Member m = mc.createMember("Joe", "joe@joe", "12121212");
		rdc.findByEmail("joe@joe");
		Dog fido = rdc.registerDog("Fido", LocalDate.now().getYear());
		//DogView dv = new DogView(fido, rdc);
		//dv.setVisible(true);
		System.out.println(m);
	}
}
