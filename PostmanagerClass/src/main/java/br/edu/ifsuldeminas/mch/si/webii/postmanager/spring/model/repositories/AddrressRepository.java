package br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.si.webii.postmanager.spring.model.Address;

@Repository
public interface AddrressRepository extends JpaRepository<Address, Integer> {
	
}
