package com.andieguo.spring.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andieguo.spring.bean.User;
import com.andieguo.spring.dao.UserDao;
import com.andieguo.spring.validate.UserFormValidator;

@Controller
public class UserController {
	private final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	UserFormValidator userFormValidator;
	
	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}
	
	// list page
	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		logger.debug("showAllUsers()");
		model.addAttribute("users", userDao.findAll());
		return "users/list";

	}

	// save or update user
	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, 
			final RedirectAttributes redirectAttributes) {
	
		logger.debug("saveUser() : "+user);
	
		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "users/addUser";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			userDao.save(user);
			return "redirect:/users/list";
		}
	
	}
	
	// show add user form
	@RequestMapping(value = "/users/addUI", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {

		logger.debug("showAddUserForm()");

		User user = new User();

		user.setName("mkyong123");
		user.setAge(20);
		user.setGender(0);
		model.addAttribute("userForm", user);

		populateDefaultModel(model);

		return "users/addUser";

	}
	
	// show update form
	@RequestMapping(value = "/users/{id}/updateUI", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateUserForm() : "+id);

		User user = userDao.findById(id);
		model.addAttribute("userForm", user);
		
		populateDefaultModel(model);
		
		return "users/updateUser";

	}
	
	
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, 
			final RedirectAttributes redirectAttributes) {
	
		logger.debug("updateUser() : "+user);
	
		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "users/updateUser";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			userDao.update(user);
			return "redirect:/users/list";
		}
	
	}
	
	

	// delete user
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, 
		final RedirectAttributes redirectAttributes) {

		logger.debug("deleteUser() : "+id);

		userDao.delete(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
		
		return "redirect:/users/list";

	}
	

	// show user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {

		logger.debug("showUser() id: "+ id);

		User user = userDao.findById(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "users/show";

	}
	
	private void populateDefaultModel(Model model) {

		List<String> frameworksList = new ArrayList<String>();
		frameworksList.add("Spring MVC");
		frameworksList.add("Struts 2");
		frameworksList.add("JSF 2");
		frameworksList.add("GWT");
		frameworksList.add("Play");
		frameworksList.add("Apache Wicket");
		model.addAttribute("frameworkList", frameworksList);

		Map<String, String> skill = new LinkedHashMap<String, String>();
		skill.put("Hibernate", "Hibernate");
		skill.put("Spring", "Spring");
		skill.put("Struts", "Struts");
		skill.put("Groovy", "Groovy");
		skill.put("Grails", "Grails");
		model.addAttribute("javaSkillList", skill);

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		model.addAttribute("numberList", numbers);

		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("US", "United Stated");
		country.put("CN", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		model.addAttribute("countryList", country);

	}


}
