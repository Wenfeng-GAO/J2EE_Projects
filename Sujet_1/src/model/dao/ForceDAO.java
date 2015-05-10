package model.dao;

import java.util.List;

import model.Force;
import model.User;
import model.db.DatabaseAccessError;

public interface ForceDAO {
	
	public List<Force> findForces(User user) throws DatabaseAccessError;
	public boolean dropForce(Force force) throws DatabaseAccessError;
	public boolean addForce(Force force) throws DatabaseAccessError;

}
