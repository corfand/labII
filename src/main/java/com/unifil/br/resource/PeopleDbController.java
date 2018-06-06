package com.unifil.br.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unifil.br.entity.People;
import com.unifil.br.repository.PeopleRepository;

@RestController
public class PeopleDbController {
		
	@Autowired
	PeopleRepository repository;
	
	public PeopleDbController(PeopleRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(path="/api/pessoaDb", method = RequestMethod.GET)
	public List<People> lista() {
		return this.repository.findAll();
	}

	@RequestMapping(path="/api/pessoaDb/{id}", method = RequestMethod.GET)
	public People pessoa(@PathVariable Long id) {
		
		return this.repository.findOne(id);

	}

	@PostMapping("/api/pessoaDb")
	public People create(@ModelAttribute("people") People people) {
		//System.out.println("AAAAAAH: " + people.getName());
		//return people;
		return this.repository.save(new People(people.getName()));
	}
	
	@RequestMapping(path = "/api/pessoaDb/{id}", method = RequestMethod.PUT)
	public People update(@PathVariable Long id, String name) {
		
		People findPeople = this.repository.findOne(id);
		if (findPeople != null) {
			findPeople.setId(findPeople.getId());
			findPeople.setName(name);
		}
		return this.repository.save(findPeople);
	}

}
