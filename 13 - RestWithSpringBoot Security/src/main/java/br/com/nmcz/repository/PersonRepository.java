package br.com.nmcz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nmcz.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}