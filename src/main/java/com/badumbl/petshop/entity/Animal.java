package com.badumbl.petshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="animal")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="nickname")
	private String nickName;
	
	@Column(name ="sex")
	private String sex;
	
	@Column(name ="species")
	private String species;
	
	@Column(name ="price")
	private double price;
	
	@Column(name ="qty")
	private int qty;

	public Animal() {
	}

	
	public Animal(String nickName, String sex, String species, double price, int qty) {
		this.nickName = nickName;
		this.sex = sex;
		this.species = species;
		this.price = price;
		this.qty = qty;
	}


	public Animal(int id, String nickName, String sex, String species, double price, int qty) {
		this.id = id;
		this.nickName = nickName;
		this.sex = sex;
		this.species = species;
		this.price = price;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
