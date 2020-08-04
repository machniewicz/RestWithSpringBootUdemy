package br.com.nmcz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import br.com.nmcz.data.vo.v1.PersonVO;
import br.com.nmcz.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices services;

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll() {
		List<PersonVO> persons = services.findAll();
		persons.stream().forEach(p ->p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
		
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVo = services.findById(id);
		personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVo;
	}
	
	@PostMapping (produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVo = services.create(person);
		personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
		return personVo;
	}
	
	@PutMapping (produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVo = services.update(person);
		personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
		return personVo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}