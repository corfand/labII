package com.unifil.br.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unifil.br.entity.People;

@RestController
public class PeopleController {
	private int i = 0;
	private List<People> people;
	
	public PeopleController() {
		people = new ArrayList<People>();
	}
	
	@RequestMapping(value="/api/pessoa", method = RequestMethod.GET)
	public Iterator<People> lista() {
		
		return people.listIterator();

	}
	
	@RequestMapping(value="/api/pessoa", method = RequestMethod.POST)
	public People create(String name) {
		++i;
		People p1 = new People(i, name);
		
		people.add(p1);
		
		return p1;
	}
	
	
	@RequestMapping(value="/api/pessoa/{id}", method = RequestMethod.GET)
	public People pessoa(@PathVariable Integer id) {

		return search("get", id, "");

	}
	
	@RequestMapping(value="/api/pessoa/{id}", method = RequestMethod.PUT)
	public People update(@PathVariable Integer id, String name) {

		return search("update", id, name);

	}

	@RequestMapping(value="/api/pessoa/{id}", method = RequestMethod.DELETE)
	public People delete(@PathVariable Integer id) {

		return search("delete", id, "");

	}
	
	public People search(String action, int id, String name){
        People _temp = new People();
        for (ListIterator<People> iter = people.listIterator(); iter.hasNext(); ) {
            People element = iter.next();
            if (element.getId() == id) {
            	if (action.equals("get")) {
                    _temp.setId(element.getId());
                    _temp.setName(element.getName());
                    break;
                }
                if (action.equals("update")) {
                    _temp.setId(element.getId());
                    _temp.setName(name);
                    people.set(iter.previousIndex(), _temp);
                    break;
                }
                if (action.equals("delete")) {
                    _temp.setId(element.getId());
                    _temp.setName(element.getName());
                    people.remove(iter.previousIndex());
                    break;
                }
            }
        }
        return _temp;
    }

}
