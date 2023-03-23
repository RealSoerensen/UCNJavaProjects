package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dal.DataAccessException;
import dal.DbConnection;
import dal.MemberDB;
import model.Member;

public class MemberController {
	DbConnection conn = DbConnection.getInstance();
	MemberDB memberdb = new MemberDB();

	public Member createMember(String name, String email, String phone) {
		Member member = null;
		try {
			member = memberdb.createWithIdentity(name, phone, email);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}
	
	public Member findByEmail(String email) {
		Member member = null;
		try {
			member = memberdb.findByEmail(email, false);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	public List<Member> getAllMembers(){
		List<Member> members = null;
		try {
			members = memberdb.getAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return members;
	}
}
