package model;

import java.util.ArrayList;
import java.util.List;

public class MemberCont {

	private static MemberCont instance;
	private ArrayList<Member> members;

	public static MemberCont getInstance() {
		if(instance == null) {
			instance = new MemberCont();
		}
		return instance;
	}
	
	private MemberCont() {
		members = new ArrayList<>();
	}
	
	public void addMember(Member m) throws IllegalArgumentException {
		if(findByEmail(m.getEmail()) == null) {
			members.add(m);
		} else {
			throw new IllegalArgumentException("Email in use " + m.getEmail());
		}
	}

	public Member findByEmail(String email) {
		Member res = null;
		for(int i = 0 ; i < members.size() && res == null; i++) {
			if(members.get(i).getEmail().equalsIgnoreCase(email)) {
				res = members.get(i);
			}
		}
		return res;
	}

	public List<Member> findAll() {
		return new ArrayList<>(this.members);
	}

}
