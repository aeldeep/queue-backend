package com.eldeep.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//This is all we need
public interface UsersDao extends JpaRepository<UsersModel,Integer>
{
	public UsersModel findByUsernameAndPassword(String username, String password);
}