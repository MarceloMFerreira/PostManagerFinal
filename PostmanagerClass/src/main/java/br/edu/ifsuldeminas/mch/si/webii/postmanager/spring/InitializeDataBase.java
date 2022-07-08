package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Address;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Company;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.User;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.AddrressRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.CompanyRepository;
import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories.UserRepository;

@Component
public class InitializeDataBase implements CommandLineRunner {
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private CompanyRepository cRepo;
	
	@Autowired
	private AddrressRepository aRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Address aMarcelo = new Address();
		aMarcelo.setPlace("Rua A");
		aMarcelo.setNumber(777);
		aMarcelo.setZipCode("123");
		
		Address aVics = new Address();
		aVics.setPlace("Rua B");
		aVics.setNumber(888);
		aVics.setZipCode("456");
		
		aRepo.save(aMarcelo);
		aRepo.save(aVics);
		aRepo.flush();
		
		User marcelo = new User();
		marcelo.setName("Marcelo Ferreira");
		marcelo.setEmail("email@gmail.com");
		marcelo.setGender("M");
		marcelo.setAddress(aMarcelo);
		
		
		User vics = new User();
		vics.setName("Vic Vics");
		vics.setEmail("email2@gmail.com");
		vics.setGender("F");
		vics.setAddress(aVics);
		
		uRepo.save(marcelo);
		uRepo.save(vics);
		
		Company facebook = new Company();
		facebook.setNome("Facebook");
		facebook.setEndereco("Rua 123, Bairro - 512");
		facebook.setContato("(35) 3232-3232");
		cRepo.save(facebook);
		
		
	}
	
}
