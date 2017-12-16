package fr.iocean.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import fr.iocean.constraint.Isbn;

@Entity
@Table
public class Book {
	
	@Id
	@Min(value=5)
	private Long id;
	
	@Column(name="title")
	@NotBlank
	private String title;
	
	@Column(name="nb_pages")
	@NotNull
	private int nbPages;
	
	@Column(name="author")
	@NotBlank
	private String author;
	
	@Column(name="publication_date")
	@Temporal(TemporalType.DATE)
	private Date publicationDate;

	@Isbn
	@Column(name="isbn")
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
