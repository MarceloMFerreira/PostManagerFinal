package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.City;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.User;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.AddrressRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CityRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddrressRepository addressRepo;
	
	@Autowired
	private CityRepository cityRepo;

	@GetMapping("/users")
	public String users(Model model) {
		List<User> users = userRepo.findAll();

		model.addAttribute("users", users);

		return "index";
	}
	
	@ModelAttribute("cities")
    public List<City> getCidades(){
        List<City> cidades = cityRepo.findAll();
        return cidades;
    }

	@GetMapping("/users/form")
	public String userForm(@ModelAttribute("user") User user) {

		return "user_form";
	}

	@PostMapping("/users/new")
	public String userNew(@Valid @ModelAttribute("user") User user, BindingResult br) {

		if(br.hasErrors()) {
			return "user_form";
		}
	
		addressRepo.save(user.getAddress());
		userRepo.save(user);

		return "redirect:/users";
	}
	
	@GetMapping("/users/{id}")
	public String userUpdate(@PathVariable("id") Integer id, Model model) {
		
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isEmpty())
			throw new IllegalArgumentException("Usuário inválido");
		
		User user = userOpt.get();
		model.addAttribute("user", user);
		return "user_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String userDelete(@PathVariable("id") Integer id) {
		
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isEmpty())
			throw new IllegalArgumentException("Usuário inválido");
		User user = userOpt.get();
		userRepo.delete(user);
		
		return "redirect:/users";
	}
}
