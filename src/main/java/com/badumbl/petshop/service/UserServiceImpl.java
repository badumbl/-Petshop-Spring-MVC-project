package com.badumbl.petshop.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.badumbl.petshop.dao.RoleDao;

import com.badumbl.petshop.dao.UserDao;

import com.badumbl.petshop.entity.Role;
import com.badumbl.petshop.entity.User;
import com.badumbl.petshop.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		user.setBudget(crmUser.getBudget());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

		 // save user in the database
		userDao.save(user);
		
	}
	@Override
	@Transactional
	public void saveForUpdate(User theUser) {
		
		userDao.saveForUpdate(theUser);
		
	}

	@Override
	@Transactional
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User findById(int theId) {		
		return userDao.findById(theId);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
	userDao.deleteById(theId);		
	}
	public void reduceBudget(double thePrice) {
		userDao.reduceBudget(thePrice);
	}

}
