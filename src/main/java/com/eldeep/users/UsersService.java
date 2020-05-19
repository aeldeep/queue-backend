package com.eldeep.users;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UsersService 
{

	public List<UsersModel> getAllUsers();

	public UsersModel saveNewUser(UsersModel u);

	public UsersModel getUserByID(int id);

	public UsersModel updateUser(UsersModel u);

	public UsersModel loginUser(String username, String password);
}
