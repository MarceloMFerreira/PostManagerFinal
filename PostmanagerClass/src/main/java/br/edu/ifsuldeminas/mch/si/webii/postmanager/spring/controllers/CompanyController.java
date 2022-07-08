package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Company;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CompanyRepository;

@Controller
public class CompanyController {
	@Autowired
	private CompanyRepository companyRepo;

	@GetMapping("/companies")
	public String companies(Model model) {
		List<Company> companies = companyRepo.findAll();

		model.addAttribute("companies", companies);

		return "company";
	}

	@GetMapping("/companies/form")
	public String companyForm(@ModelAttribute("company") Company company) {

		return "company_form";
	}

	@PostMapping("/companies/new")
	public String companyNew(@ModelAttribute("company") Company company) {

		companyRepo.save(company);

		return "redirect:/companies";
	}
	
	@GetMapping("/companies/{id}")
	public String companyUpdate(@PathVariable("id") Integer id, Model model) {
		
		Optional<Company> companyOpt = companyRepo.findById(id);
		if(companyOpt.isEmpty())
			throw new IllegalArgumentException("Empresa inválida");
		
		Company company = companyOpt.get();
		model.addAttribute("company", company);
		return "company_form";
	}
	
	@GetMapping("/companies/delete/{id}")
	public String companyDelete(@PathVariable("id") Integer id) {
		
		Optional<Company> companyOpt = companyRepo.findById(id);
		if(companyOpt.isEmpty())
			throw new IllegalArgumentException("Empresa inválida");
		Company company = companyOpt.get();
		companyRepo.delete(company);
		
		return "redirect:/companies";
	}

}
