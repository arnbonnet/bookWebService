package fr.iocean.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.App;
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

	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long createBook(@RequestBody @Valid Book input, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult.getErrorCount());
			for (ObjectError e: bindingResult.getAllErrors()) {
				System.out.println(e.getCode()+" " +e.getDefaultMessage());
				throw new Exception(e.getDefaultMessage());
			}
		}
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		LibraryService libraryService = context.getBean(LibraryService.class);
		
		libraryService.create(input);
		context.close();
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
		library.delete(id);
		System.out.println("After remove: " + library.getAll().size());
		
		return id;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public Book getById(@PathVariable Long id) {
		LibraryService library = LibraryService.getInstance();
		return library.getById(id);
	}
}
