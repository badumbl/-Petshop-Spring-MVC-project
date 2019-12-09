package com.badumbl.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badumbl.petshop.dao.AnimalDao;
import com.badumbl.petshop.dao.AnimalRepository;
import com.badumbl.petshop.entity.Animal;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	AnimalDao animalDao;

	@Override
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}

	@Override
	public Animal findById(int theId) {
		Optional<Animal> result = animalRepository.findById(theId);
		Animal theAnimal = null;
		if (result.isPresent()) {
			theAnimal = result.get();
		} else {
			throw new RuntimeException("did not find animal id -" + theId);
		}
		return theAnimal;
	}

	@Override
	public void save(Animal theAnimal) {
		animalRepository.save(theAnimal);
	}

	@Override
	public void deleteById(int theId) {
		animalRepository.deleteById(theId);
	}

	@Override
	  public void reduceQty(int theId) { 
		 animalDao.reduceQty(theId); }
	 

}
