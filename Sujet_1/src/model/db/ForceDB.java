package model.db;

import java.util.List;

import model.Force;
import model.User;
import model.dao.ForceDAO;

public class ForceDB implements ForceDAO {

	@Override
	public List<Force> findForces(User user) throws DatabaseAccessError {
		return null;
	}

	@Override
	public boolean dropForce(Force force) throws DatabaseAccessError {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addForce(Force force) throws DatabaseAccessError {
		// TODO Auto-generated method stub
		return false;
	}

}
