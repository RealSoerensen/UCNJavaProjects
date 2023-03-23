package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import control.MemberController;
import dal.DbConnection;
import model.Member;

class Testing {
	MemberController memberController = new MemberController();

	@Test
	void testDBConnection() {
		 try {
			 Connection c = DbConnection.getInstance().getConnection();
			 assertNotNull(c);
		 } catch (Exception e) {
			 fail("issues with dbconnection");
		 }
	}
	
	
	@Test
	void testMemberFindByEmail() {
		try {
			Member member = memberController.findByEmail("test@gmail.com");
			assertNotNull(member);
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testMemberGetAll() {
		try {
			List<Member> members = memberController.getAllMembers();
			assertEquals(4, members.size());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testCreateDog() {
		try {
			
			
		} catch(Exception e) {
			
		}
	}

}
