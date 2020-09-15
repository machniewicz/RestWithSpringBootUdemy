package br.com.nmcz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nmcz.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
