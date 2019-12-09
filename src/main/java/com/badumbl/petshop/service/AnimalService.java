package com.badumbl.petshop.service;

import java.util.List;

import com.badumbl.petshop.entity.Animal;

public interface AnimalService {
	
	public List<Animal> findAll();

	public Animal findById(int theId);

	public void save(Animal theAnimal);

	public void deleteById(int theId);
	
	 public void reduceQty(int theId); 
}
