package com.badumbl.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.badumbl.petshop.entity.User;
import com.badumbl.petshop.service.UserService;
import com.badumbl.petshop.user.CrmUser;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userService;
	
	@GetMapping("/list")
	public String findAllUsers(Model theModel) {
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		return "users-list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
		User theUser = userService.findById(theId);
		theModel.addAttribute("user", theUser);
		return "user-form";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User theUser) {
		userService.saveForUpdate(theUser);
		return "redirect:/users/list";
	}
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int theId, Model theModel) {
		userService.deleteById(theId);
		return "redirect:/users/list";
	}
}
