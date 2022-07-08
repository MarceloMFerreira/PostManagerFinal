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
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CityRepository;

@Controller
public class CItyController {
	@Autowired
	private CityRepository cityRepo;

	@GetMapping("/cities")
	public String cities(Model model) {
		List<City> cities = cityRepo.findAll();

		model.addAttribute("cities", cities);

		return "city";
	}

	@GetMapping("/cities/form")
	public String cityForm(@ModelAttribute("city") City city) {

		return "city_form";
	}

	@PostMapping("/cities/new")
	public String cityNew(@Valid @ModelAttribute("city") City city, BindingResult br) {
		if(br.hasErrors()) {
			return "city_form";
		}

		cityRepo.save(city);

		return "redirect:/cities";
	}

	@GetMapping("/cities/{id}")
	public String cityUpdate(@PathVariable("id") Integer id, Model model) {

		Optional<City> cityOpt = cityRepo.findById(id);
		if (cityOpt.isEmpty())
			throw new IllegalArgumentException("Cidade inválida");

		City city = cityOpt.get();
		model.addAttribute("city", city);
		return "city_form";
	}

	@GetMapping("/cities/delete/{id}")
	public String cityDelete(@PathVariable("id") Integer id) {

		Optional<City> cityOpt = cityRepo.findById(id);
		if (cityOpt.isEmpty())
			throw new IllegalArgumentException("Cidade inválida");
		City city = cityOpt.get();
		cityRepo.delete(city);

		return "redirect:/cities";
	}

}
