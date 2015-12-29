package yh.dao;

import yh.model.User;

public interface UserDao {

	public User findByName(String username); 

}
