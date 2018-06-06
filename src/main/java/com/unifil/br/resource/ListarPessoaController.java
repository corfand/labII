package com.unifil.br.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.unifil.br.repository.PeopleRepository;

@Controller
public class ListarPessoaController {
	
	@Autowired
	PeopleRepository repository;
	
	//GET
	@RequestMapping(path="/listarpessoa", method = RequestMethod.GET)
	public String listarPessoa(Map<String, Object> model) {
		model.put("lista", this.repository.findAll());
		model.put("developer", "Andréa");
		return "listarpessoa";
	}
}
