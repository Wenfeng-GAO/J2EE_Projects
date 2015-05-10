package model.dao;

import java.util.List;

import model.User;
import model.db.DatabaseAccessError;

public interface UserDAO {

	public boolean updateUser(User user) throws DatabaseAccessError;
	public User getUserByEmail(String email) throws DatabaseAccessError;
	public List<User> findUsersBy(String s) throws DatabaseAccessError;
	
}
