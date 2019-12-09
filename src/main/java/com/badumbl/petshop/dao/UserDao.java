package com.badumbl.petshop.dao;

import java.util.List;

import com.badumbl.petshop.entity.User;

public interface UserDao {

	public List<User> findAll();
	public User findById(int theId);
    public User findByUserName(String userName);
    public void deleteById(int theId);
    public void save(User user);
    public void saveForUpdate(User theUser);
	public void deleteRoleById(int theId);
	public void reduceBudget(double thePrice) ;
	public User findByName(String theName);
    
}
