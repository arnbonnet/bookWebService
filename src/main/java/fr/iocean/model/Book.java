package fr.iocean.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import fr.iocean.constraint.Isbn;

public class Book {
	
	@Min(value=5)
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotNull
	private int nbPages;
	
	@NotBlank
	private String author;
	
	private Date publicationDate;
	
//	@Size(min=10, max=14)
//	@NotBlank
	@Isbn
	private String isbn;
	
	public Book() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", nbPages=" + nbPages + ", author=" + author
				+ ", publicationDate=" + publicationDate + ", isbn=" + isbn + "]";
	}


}
