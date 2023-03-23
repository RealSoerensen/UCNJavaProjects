package dal;

import java.util.List;

import model.Dog;

public interface DogDBIF {

	 Dog createWithIdentity(String chip, String name, int feeYear, int memberId) throws DataAccessException;
	 	 
	 Dog findByChip(String chip) throws DataAccessException;	 
	 
	 List<Dog> findByMemberId(int id) throws DataAccessException;
	 
	 //TODO missing some methods
	
}
