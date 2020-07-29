package br.com.nmcz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nmcz.converter.DozerConverter;
import br.com.nmcz.data.model.Person;
import br.com.nmcz.data.vo.PersonVO;
import br.com.nmcz.exception.ResouceNotFoundException;
import br.com.nmcz.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(),PersonVO.class);
	}

	public PersonVO findById(Long id) {
		var entity =  repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("No records for this ID!"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResouceNotFoundException("No records for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("No records for this ID!"));
		repository.delete(entity);

	}

}
