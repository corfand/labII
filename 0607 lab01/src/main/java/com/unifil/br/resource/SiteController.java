package com.unifil.br.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unifil.br.entity.Departamento;
import com.unifil.br.entity.People;
import com.unifil.br.repository.DepartamentoRepository;
import com.unifil.br.repository.PeopleRepository;

@Controller
public class SiteController {
	@Autowired
	PeopleRepository repository;
	
	@Autowired
	DepartamentoRepository drepository;
	
	public SiteController(PeopleRepository repository, DepartamentoRepository drepository) {
		this.drepository = drepository;
		this.repository = repository;
	}
	
	//GET
	@RequestMapping(path="/Teste", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		List<String> listalivro = new ArrayList<String>();
	
		listalivro.add("Java");
		listalivro.add("Eclipse");
		listalivro.add("Spring Boot");
		listalivro.add("Mustache");
		listalivro.add("Bootstrap");
		listalivro.add("MySql");
		
		model.put("lista", listalivro);
		model.put("developer", "Andréa Corrêa");
		
		return "index";
	}
	
	@PostMapping("/pessoa")
	public String createPessoa(@ModelAttribute("pessoa") People pessoa) {
		
		this.repository.save(new People(pessoa.getName()));
		
		System.out.println(pessoa.getName());
		
		return "redirect:/pessoa/lista";
	}
	
	//GET
	//@RequestMapping(path="/pessoa", method = RequestMethod.GET)
	@GetMapping("/pessoa")
	public String addPessoa(Map<String, Object> model) {
		/*Outra forma de fazer
		People pessoa = new People();
	
		model.put("pessoa", pessoa);*/
		
		model.put("pessoa", new People());
		model.put("departamentos", this.drepository.findAll());
		return "pessoa/new";
	}
	
	@GetMapping("/pessoa/lista")
	public String listaPessoa(Map<String, Object> model) {
		
		model.put("pessoas", this.repository.findAll());
		
		return "pessoa/index";
	}

	@RequestMapping(path="/pessoa/edit/{id}", method = RequestMethod.GET)
	public String editPessoa(@PathVariable Long id, Map<String, Object> model) {
		
		model.put("pessoa", this.repository.findOne(id));
		
		return "pessoa/edit";

	}

	@RequestMapping(path="/pessoa/{id}", method = RequestMethod.PUT)
	public String updatePessoa(@PathVariable Long id, String name) {
		
		People p = this.repository.findOne(id);
		
		if (p != null) {
			p.setName(name);
			this.repository.save(p);
		} else {
			System.out.println("Pessoa não existe");
		}
		
		return "redirect:/pessoa/lista";

	}
	
	@DeleteMapping("pessoa/{id}")
	public String delete(@PathVariable long id) {
		
		this.repository.delete(id);
		
		return "redirect:/pessoa/lista";
	}
}