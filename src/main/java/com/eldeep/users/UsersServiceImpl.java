package com.eldeep.users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eldeep.exceptions.ValidInformation;


@Service
public class UsersServiceImpl implements UsersService{
	
	private UsersDao ud;
	
	@Autowired// tells spring to find a bean of type UsersDao and pass it in here
	public UsersServiceImpl(UsersDao ud) {
		this.ud = ud;
	}

	@Override
	public List<UsersModel> getAllUsers() {
		return ud.findAll();
	}

	@Override
	public UsersModel saveNewUser(UsersModel u) {
		
		return ud.save(u);
	}

	@Override
	public UsersModel getUserByID(int id) {
		return ud.getOne(id);
	}

	@Override
	@Transactional// is from spring transactions, its special annotation we can put on a method that makes everything in that method become a database transaction
	public UsersModel updateUser(UsersModel u) {
		
		//To do an update we leverage hibernate automatic dirty checking
		//we find the object to update and thern set whatever fields to their new values
		// make sure to do it in one transaction using the @transactional annotation
		UsersModel oldUser = ud.getOne(u.getUserId());
		if(u.getFirstName() != null) {
			oldUser.setFirstName(u.getFirstName());
		}
		if(u.getLastName() != null) {
			oldUser.setLastName(u.getLastName());
		}
		if(u.getUsername() != null) {
			oldUser.setUsername(u.getUsername());
		}
		if(u.getPassword() != null) {
			oldUser.setPassword(u.getPassword());
		}
		return oldUser;
	}

	@Override
	public UsersModel loginUser(String username, String password) {
		// TODO Auto-generated method stub
		if (ud.findByUsernameAndPassword(username, password)==null)
		{
			throw new ValidInformation();
		}else {
			return ud.findByUsernameAndPassword(username, password);
	}
	}

}
