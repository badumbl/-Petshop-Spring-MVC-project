package com.badumbl.petshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.badumbl.petshop.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		return theUser;
	}

	@Override
	public void save(User theUser) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theqQuery = currentSession.createQuery("from User", User.class);
		List<User> theUsers = theqQuery.getResultList();
		return theUsers;
	}

	@Override
	public User findById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		User theUser = currentSession.get(User.class, theId);
		return theUser;
	}
	
	@Override
	public User findByName(String theName) {
		Session currentSession = entityManager.unwrap(Session.class);
		User theUser = currentSession.get(User.class, theName);
		return theUser;
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		deleteRoleById(theId);
		Query theQuery = currentSession.createQuery("delete from User where id=:userId");
		theQuery.setParameter("userId", theId);
		theQuery.executeUpdate();

	}

	@Override
	@Transactional
	public void deleteRoleById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query theQuery = currentSession.createQuery("delete from UserRole where id=:userId");
		theQuery.setParameter("userId", theId);
		theQuery.executeUpdate();

	}

	@Override
	@Transactional
	public void saveForUpdate(User theUser) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theUser);
	}
	@Override
	@Transactional
	public void reduceBudget(double thePrice) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName =  auth.getName();
        User user = findByUserName(userName);
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("update User set budget=:theBudget" +" where id=:userId");
		theQuery.setParameter("userId", user.getId());
		theQuery.setParameter("theBudget", (user.getBudget()-thePrice));
		theQuery.executeUpdate();
	}

}
