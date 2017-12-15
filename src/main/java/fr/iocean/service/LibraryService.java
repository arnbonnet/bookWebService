package fr.iocean.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.iocean.model.Book;

@Service
public class LibraryService {

	public static LibraryService instance = null;

	public static LibraryService getInstance() {
		if (instance != null)
			return instance;
		else
			instance = new LibraryService();
		return instance;
	}

	private ArrayList<Book> list = new ArrayList<Book>();

	public boolean addBook(Book b) {
		if (list.contains(b.getId()))
			return false;
		list.add(b);
		return true;
	}

	public boolean removeBook(Long id) {
		for (Book b : list)
			if (b.getId() == id) {
				list.remove(b);
				return true;
			}
		return false;
	}

	// public ArrayList<Book> search(String criteria){
	// ArrayList<Book> result = new ArrayList<Book>();
	// for(Book b: list)if(b.matches(criteria.toLowerCase()))result.add(b);
	// return result;
	// }

	public boolean contains(int id) {
		return getById(id) != null;
	}

	public Book getById(int id) {
		for (Book b : list)
			if (b.getId() == id)
				return b;
		return null;
	}

	public List<Book> getAll() {
		return list;
	}

	public void update(Book b) {
		for (Book bList : list) {
			if (bList.getId() == b.getId()) {
				list.remove(bList);
				list.add(b);
				break;
			}
		}
	}

	private LibraryService() {

		Book book = new Book();
		book.setId(1L);
		book.setTitle("L'Assassin royal - Tome 1");
		book.setAuthor("Robin Hobb");
		book.setNbPages(400);
		book.setPublicationDate(new Date());

		Book book2 = new Book();
		book2.setId(2L);
		book2.setTitle("L'Assassin royal - Tome 2");
		book2.setAuthor("Robin Hobb");
		book2.setNbPages(400);
		book2.setPublicationDate(new Date());

		Book book3 = new Book();
		book3.setId(3L);
		book3.setTitle("L'Assassin royal - Tome 3");
		book3.setAuthor("Robin Hobb");
		book3.setNbPages(400);
		book3.setPublicationDate(new Date());

		list.add(book);
		list.add(book2);
		list.add(book3);
	}

}
