package fr.iocean.model;

import java.util.Date;

public class Book {
	
	private Long id;
	
	private String title;
	
	private int nbPages;
	
	private String author;
	
	private Date publicationDate;
	
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

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", nbPages=" + nbPages + ", author=" + author
				+ ", publicationDate=" + publicationDate + "]";
	}
	
	
	

}
