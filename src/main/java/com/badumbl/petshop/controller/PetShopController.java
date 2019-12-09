package com.badumbl.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.badumbl.petshop.entity.Animal;
import com.badumbl.petshop.entity.User;
import com.badumbl.petshop.service.AnimalService;
import com.badumbl.petshop.service.UserService;

@Controller
@RequestMapping("/animals")
public class PetShopController {

	@Autowired
	AnimalService animalService;
	
	@Autowired
	UserService userService;

	@GetMapping("/list")
	public String listOfAnimals(Model theModel) {
		List<Animal> theAnimals = animalService.findAll();
		theModel.addAttribute("animals", theAnimals);
		theModel.addAttribute("user", userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "animals-list";
	}

	@GetMapping("/delete")
	public String deleteAnimal(@RequestParam("animalId") int theId) {
		animalService.deleteById(theId);
		return "redirect:/animals/list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Animal theAnimal = new Animal();
		theModel.addAttribute("animal", theAnimal);
		return "animal-form";
	}

	@PostMapping("/save")
	public String addAnimal(@ModelAttribute("animal") Animal theAnimal) {
		animalService.save(theAnimal);
		return "redirect:/animals/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("animalId") int theId, Model theModel) {
		Animal theAnimal = animalService.findById(theId);
		theModel.addAttribute("animal", theAnimal);
		return "animal-form";
	}
	@GetMapping("/purchase")
	public String purchaseAnimal(@RequestParam("animalId") int theId, Model theModel) {
		animalService.reduceQty(theId);
		userService.reduceBudget(animalService.findById(theId).getPrice());
		return "purchase-confirmation";
	}
}
