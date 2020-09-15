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

import br.com.nmcz.data.vo.v1.BookVO;
import br.com.nmcz.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book EndPoint", description = "Description for Books", tags= {"Book EndPoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookServices services;

	@ApiOperation(value = "Find All Books")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = services.findAll();
		books.stream().forEach(p ->p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
		
	}

	@ApiOperation(value = "Find Book for ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVo = services.findById(id);
		bookVo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVo;
	}
	
	@ApiOperation(value = "Create new record Book")
	@PostMapping (produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVo = services.create(book);
		bookVo.add(linkTo(methodOn(BookController.class).findById(bookVo.getKey())).withSelfRel());
		return bookVo;
	}
	
	@ApiOperation(value = "Update Book")
	@PutMapping (produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVo = services.update(book);
		bookVo.add(linkTo(methodOn(BookController.class).findById(bookVo.getKey())).withSelfRel());
		return bookVo;
	}
	
	@ApiOperation(value = "Remove Book")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}