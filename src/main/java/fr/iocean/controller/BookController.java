package fr.iocean.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.model.Book;

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
	public Book getBooks() {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("L'Assassin royal - Tome 1");
		book.setAuthor("Robin Hobb");
		book.setNbPages(400);
		book.setPublicationDate(new Date());
		
		return book;
	}
}
