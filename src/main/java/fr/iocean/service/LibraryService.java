package fr.iocean.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.model.Book;

@Service
@Transactional
public class LibraryService {
	
	@PersistenceContext
	protected EntityManager em;

	public static LibraryService instance = null;
	
	public LibraryService() {
	}

	public static LibraryService getInstance() {
		if (instance != null)
			return instance;
		else
			instance = new LibraryService();
		return instance;
	}

	private ArrayList<Book> list = new ArrayList<Book>();

	public boolean contains(Long id) {
		return getById(id) != null;
	}
	
	public void create(Book book) {
		em.persist(book);
	}

	public void update(Book book) {
		em.merge(book);
	}

	public void delete(Long id) {
		em.remove(getById(id));
	}

	public List<Book> getAll() {
		TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);

		List<Book> books = query.getResultList();

		return books;
	}

	public Book getById(Long id) {
		TypedQuery<Book> query = em.createQuery("select b from Book b where b.id =:bookId ", Book.class)
									.setParameter("bookId", id);

		return query.getSingleResult();
	}

}
