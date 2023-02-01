package ctrl;

import java.util.List;

import model.Member;
import model.MemberCont;

public class MemberCtrl {

	public Member findByEmail(String email) {//1.1
		MemberCont mc = MemberCont.getInstance();
		Member m = mc.findByEmail(email);//1.1.1:...
		return m;
	}

	public Member createMember(String name, String email, String phone) {
		Member m = new Member(name, email, phone);
		MemberCont.getInstance().addMember(m);
		return m;
	}

	public List<Member> findAll() {
		return MemberCont.getInstance().findAll();
	}

}
