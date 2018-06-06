package com.unifil.br.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiteController {
	
	
	//GET
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		List<String> listalivro = new ArrayList<String>();
	
		listalivro.add("Java");
		listalivro.add("Eclipse");
		listalivro.add("Spring Boot");
		listalivro.add("Mustache");
		listalivro.add("Bootstrap");
		listalivro.add("MySql");
		model.put("lista", listalivro);
		model.put("developer", "Andréa e Zé");
		return "index";
	}
}


/*<ul>
 * <li>Java</li>
		<li>Eclipe</li>
		<li>Spring Boot</li>
		<li>Mustache</li>
		<li>Bootstrap</li>
		<li>MySql</li>
	</ul>
	
	{{#lista}}
		{{nome}}
	{{/lista}}
	
 * 
 * 
 * 
 * 
 * */
 