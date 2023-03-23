package dal;

import java.util.List;
import model.Member;

public interface MemberDBIF {

	Member createWithIdentity(String name, String phone, String email) throws DataAccessException;

	Member findByEmail(String email, boolean retrieveAssociation) throws DataAccessException;

	List<Member> getAll() throws DataAccessException;

	//TODO some methods are missing
}
