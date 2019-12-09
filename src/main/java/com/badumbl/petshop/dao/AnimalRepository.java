package com.badumbl.petshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badumbl.petshop.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

}
