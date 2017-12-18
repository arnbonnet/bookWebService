package fr.iocean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.model.Book;
import fr.iocean.service.LibraryService;

@Sql("classpath:test-book-data.sql")
public class BookIT extends IntegrationTest {

	@Autowired
	LibraryService service;

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testCreate() throws Exception {
		Book b = new Book();
		b.setId(3l);
		b.setTitle("test title");
		b.setAuthor("test author");
		b.setNbPages(400);
		b.setIsbn("12345678912");
		b.setPublicationDate(new Date());

		this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(b))).andExpect(status().isOk());

		this.mockMvc.perform(get("/books")).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(status().isOk());

	}

	@Test
	// @WithMockUser
	public void testCreatePreconditionFail() throws Exception {
		Book b = new Book();
		b.setId(3l);
		b.setTitle("test title");
		b.setAuthor("test author");
		b.setNbPages(400);
		b.setIsbn("123456"); // ISBN too short
		b.setPublicationDate(new Date());

		this.mockMvc
				.perform(post("/books").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
						.content(jsonHelper.serialize(b)))
				.andDo(MockMvcResultHandlers.print())
				// .andExpect(jsonPath("$[*].field", containsInAnyOrder("id", "title",
				// "nbPages", "author", "isbn", "publicationDate")))
				// .andExpect(jsonPath("$[0].objectName").value("book"))
				// .andExpect(jsonPath("$[*].code", containsInAnyOrder("Isbn")))
				.andExpect(status().isPreconditionFailed());

	}

	@Test
	// @WithMockUser
	public void testUpdate() throws Exception {
		Book book = service.getById(1l);
		Assert.assertEquals("pre filled data 1", book.getTitle());

		book.setTitle("l'assassin royal - tome 1");
		this.mockMvc
				.perform(put("/books", 1l).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
						.content(jsonHelper.serialize(book)))
				// .andExpect(jsonPath("$.title").value("l'assassin royal - tome 1"))
				.andExpect(status().isOk());

		this.mockMvc.perform(get("/books/{id}", 1)).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.title").value("l'assassin royal - tome 1"))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testGetBook() throws Exception {
		this.mockMvc.perform(get("/books/{id}", 1)).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.title").value("pre filled data 1"))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testGetNotFoundBook() throws Exception {
		this.mockMvc.perform(get("/books/{id}", 55)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isNotFound());
	}

	@Test
	// @WithMockUser(authorities = "TEST")
	public void testGetAllBooks() throws Exception {
		this.mockMvc.perform(get("/books")).andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(status().isOk());
	}

}
