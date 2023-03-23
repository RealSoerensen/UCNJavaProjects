package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Dog;
import model.Member;

public class MemberDB implements MemberDBIF {

	private static final String SELECT_MEMBER_EMAIL = "select * from member where email = '%s'";
	private static final String SELECT_MEMBER_ALL = "SELECT * FROM MEMBER";
	private static final String INSERT_MEMBER = "INSERT INTO member(name, phone, email) VALUES ('%s','%s', '%s')";

	@Override
	public Member createWithIdentity(String name, String phone, String email) throws DataAccessException {

		String sqlString = String.format(INSERT_MEMBER, name, phone, email);
		int identity;
		try {
			identity = DbConnection.getInstance().executeSqlInsertWithIdentity(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cannot insert record", e);
		}
		return new Member(identity, name, phone, email);
	}


	@Override
	public Member findByEmail(String email, boolean retrieveAssociation) throws DataAccessException {
		Member m = null;
		try (Statement s = DbConnection.getInstance().getConnection().createStatement()) {
			ResultSet rs = s.executeQuery(String.format(SELECT_MEMBER_EMAIL, email));
			if (rs != null && rs.next()) {
				m = buildObject(rs, retrieveAssociation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.GENERIC_MESSAGE, e);
		}
		return m;
	}
	
	@Override
	public List<Member> getAll() throws DataAccessException {
		List<Member> lm = null;
		try (Statement s = DbConnection.getInstance().getConnection().createStatement()) {
			ResultSet rs = s.executeQuery(SELECT_MEMBER_ALL);
			if (rs != null && rs.next()) {
				lm = buildListObjects(rs,false); //do not retrieve the dogs
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.GENERIC_MESSAGE, e);
		}
		return lm;
	}
	
	private List<Member> buildListObjects(ResultSet rs, boolean retrieveAssociation) throws DataAccessException {
		List<Member> res = new ArrayList<>();
		try {
			do {
				Member m = buildObject(rs, retrieveAssociation);
				res.add(m);
			} while (rs.next());
		} catch (SQLException e) {
			throw new DataAccessException("Could not iterate result set", e);
		}
		return res;
	}

	private Member buildObject(ResultSet rs, boolean retrieveAssociation) throws DataAccessException, SQLException {
		Member member = null;
		int identity;
		try {
			identity = rs.getInt("id");
			member = new Member(identity, rs.getString("name"), rs.getString("phone"), rs.getString("email"));
		} catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		/* this code only after implementing DogDB*/
		//if (retrieveAssociation) {
		//	member.setDogs(new DogDB().findByMemberId(identity));
		//}
		return member;
	}


}
