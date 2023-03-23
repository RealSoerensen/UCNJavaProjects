package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Dog;
import model.Member;

public class DogDB implements DogDBIF{
	private static final String SELECT_DOG_CHIP = "select * from dog where chip = '%s'";
	private static final String SELECT_DOG_ALL = "SELECT * FROM dog";
	private static final String INSERT_DOG = "INSERT INTO dog(chip, name, feeYear, memberId) VALUES ('%s', '%s', '%s', %s)";

	@Override
	public Dog createWithIdentity(String chip, String name, int feeYear, int memberId) throws DataAccessException {
		String sqlString = String.format(INSERT_DOG, chip, name, feeYear, memberId);
		int identity;
		try {
			identity = DbConnection.getInstance().executeSqlInsertWithIdentity(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Cannot insert record", e);
		}
		return new Dog(identity, chip, name, feeYear);
	}

	@Override
	public Dog findByChip(String chip) throws DataAccessException {
		Dog dog = null;
		try (Statement s = DbConnection.getInstance().getConnection().createStatement()) {
			ResultSet rs = s.executeQuery(String.format(SELECT_DOG_CHIP, chip));
			if (rs != null && rs.next()) {
				dog = buildObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.GENERIC_MESSAGE, e);
		}
		return dog;
	}

	@Override
	public List<Dog> findByMemberId(int id) throws DataAccessException {
		List<Dog> dogs = null;
		try (Statement s = DbConnection.getInstance().getConnection().createStatement()) {
			ResultSet rs = s.executeQuery(SELECT_DOG_ALL);
			if (rs != null && rs.next()) {
				dogs = buildListObjects(rs,false); //do not retrieve the dogs
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.GENERIC_MESSAGE, e);
		}
		return dogs;
	}
	
	private List<Dog> buildListObjects(ResultSet rs, boolean b) throws DataAccessException {
		List<Dog> res = new ArrayList<>();
		try {
			do {
				Dog dog = buildObject(rs);
				res.add(dog);
			} while (rs.next());
		} catch (SQLException e) {
			throw new DataAccessException("Could not iterate result set", e);
		}
		return res;
	}

	private Dog buildObject(ResultSet rs) throws DataAccessException, SQLException {
		Dog dog = null;
		int identity;
		try {
			identity = rs.getInt("id");
			dog = new Dog(identity, rs.getString("chip"), rs.getString("name"), Integer.parseInt(rs.getString("feeYear")));
		} catch (SQLException e) {
			throw new DataAccessException("Cannot convert from ResultSet", e);
		}
		return dog;
	}

}
