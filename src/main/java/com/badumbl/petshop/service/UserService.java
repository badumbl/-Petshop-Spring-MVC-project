package com.badumbl.petshop.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.badumbl.petshop.entity.User;
import com.badumbl.petshop.user.CrmUser;


public interface UserService extends UserDetailsService {
	public User findByUserName(String userName);
	public List<User> findAll();
	public User findById(int theId);
	public void save(CrmUser crmUser);
	public void deleteById(int theId);
	public void saveForUpdate(User theUser);
	public void reduceBudget(double d);
}
