package fr.iocean.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.iocean.model.Book;
import fr.iocean.service.LibraryService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String foo() {
		return "Hello world";
	}
	
	@RequestMapping(value="/{year}/{month}/{day}",  method = RequestMethod.GET)
	public String findBookByDate(@PathVariable int year, @PathVariable int month, @PathVariable int day, @RequestParam String sort) {
		LocalDate date = LocalDate.of(year, month, day);
		return "Liste des livres du " + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear() + " triés par " + sort;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getBooks() {
		LibraryService library = LibraryService.getInstance();
		return library.getAll();
			
//		Book book = new Book();
//		book.setId(1L);
//		book.setTitle("L'Assassin royal - Tome 1");
//		book.setAuthor("Robin Hobb");
//		book.setNbPages(400);
//		book.setPublicationDate(new Date());
//		
//		Book book2 = new Book();
//		book2.setId(2L);
//		book2.setTitle("L'Assassin royal - Tome 2");
//		book2.setAuthor("Robin Hobb");
//		book2.setNbPages(400);
//		book2.setPublicationDate(new Date());
//		
//		Book book3 = new Book();
//		book3.setId(3L);
//		book3.setTitle("L'Assassin royal - Tome 3");
//		book3.setAuthor("Robin Hobb");
//		book3.setNbPages(400);
//		book3.setPublicationDate(new Date());
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String str = null;
//		
//		try {
//			str = objectMapper.writeValueAsString(book) + "\n" + objectMapper.writeValueAsString(book2) + "\n" + objectMapper.writeValueAsString(book3);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return str;
//		return Arrays.asList(book, book2, book3);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long createBook(@RequestBody Book input) {
		LibraryService library = LibraryService.getInstance();
		System.out.println("Before create : " + library.getAll().size());
		library.addBook(input);
		System.out.println("After create : " + library.getAll().size());
		System.out.println("input book : " + input.toString());
		
		return input.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long updateBook(@RequestBody Book input) {
		LibraryService library = LibraryService.getInstance();
		System.out.println("Before update : ");
		for(Book b : library.getAll()) {
			System.out.println(b.toString());
		}
		
		library.update(input);
		

		System.out.println("After update : " + library.getAll().size());
		for(Book b : library.getAll()) {
			System.out.println(b.toString());
		}
		
		return input.getId();
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public Long removeBook(@PathVariable Long id) {
		LibraryService library = LibraryService.getInstance();
		System.out.println("Before remove: " + library.getAll().size());
		library.removeBook(id);
		System.out.println("After remove: " + library.getAll().size());
		
		return id;
	}
}
