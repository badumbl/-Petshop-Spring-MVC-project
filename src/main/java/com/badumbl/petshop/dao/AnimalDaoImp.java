package com.badumbl.petshop.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.badumbl.petshop.entity.Animal;

@Repository
public class AnimalDaoImp implements AnimalDao {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	AnimalRepository animalRepository;

	@Transactional
	public void reduceQty(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Optional<Animal> result = animalRepository.findById(theId);
		Animal theAnimal = null;
		if (result.isPresent()) {
			theAnimal = result.get();
		} else {
			throw new RuntimeException("did not find animal id -" + theId);
		}

		if (theAnimal.getQty() > 1) {
			
			Query theQuery = currentSession.createQuery("update Animal set qty=:theQty" +" where id=:animalId");
			theQuery.setParameter("animalId", theId);
			theQuery.setParameter("theQty", (theAnimal.getQty() - 1));
			theQuery.executeUpdate();
			
		} else {
			Query theQuery = currentSession.createQuery("delete from Animal where id=:animalId");
			theQuery.setParameter("animalId", theId);
			theQuery.executeUpdate();
			
		}
	}
}
