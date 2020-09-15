package br.com.nmcz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nmcz.converter.DozerConverter;
import br.com.nmcz.data.model.Book;
import br.com.nmcz.data.vo.v1.BookVO;
import br.com.nmcz.exception.ResouceNotFoundException;
import br.com.nmcz.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	BookRepository repository;

	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(),BookVO.class);
	}

	public BookVO findById(Long id) {
		var entity =  repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("No records for this ID!"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public BookVO update(BookVO book) {
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResouceNotFoundException("No records for this ID!"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		
		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("No records for this ID!"));
		repository.delete(entity);

	}

}
