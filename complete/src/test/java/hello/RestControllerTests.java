/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.junit.Assert.*;

import hello.model.Book;
import hello.model.Genre;
import hello.repository.BookRepository;
import hello.repository.GenreRepository;
import hello.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class RestControllerTests {

	private static final Logger log = LoggerFactory.getLogger(RestControllerTests.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AdminService adminService;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private BookRepository bookRepository;


	@Test
	public void testMain() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testInserts() throws Exception {


		adminService.deleteData(); //Per si de cas, per no tenir problemes insertant

		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/rest/admin/insertData", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testRetrieve() throws Exception {

		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/rest/admin/retrieveData", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}



	@Test
	public void testBookController() throws Exception {


		adminService.deleteData(); //Per si de cas, per no tenir problemes insertant
		adminService.insertData(); //Per posar unes cuantes dades a recuperar

		//Comprovem que podem recuperar un llistat de llibres
		ResponseEntity<List> listBooksResponse = restTemplate.getForEntity("http://localhost:" + this.port + "/rest/books/", List.class);
		assertEquals(HttpStatus.OK, listBooksResponse.getStatusCode());

		List<Book> listBooks =  listBooksResponse.getBody();
		log.info("listBooks" + listBooks);
		assertTrue(listBooks.size()>1);
		int sizeBefore = listBooks.size();
		log.info("listBooks.size(): " +sizeBefore);


		//Comprovem que podem recuperar un llibre determinat
		ResponseEntity<Book> bookResponse = restTemplate.getForEntity("http://localhost:" + this.port + "/rest/books/11", Book.class);
		log.info("bookResponse" + bookResponse);
		assertEquals(HttpStatus.OK, bookResponse.getStatusCode());

		Book book = bookResponse.getBody();
		assertEquals(book.getId(), Long.valueOf(11));

		//Comprovem que podem recuperar un llistat de llibres per genere
		ResponseEntity<List> listBooksResponseByGenre = restTemplate.getForEntity("http://localhost:" + this.port + "/rest/books/genre/Scy-fi", List.class);
		assertEquals(HttpStatus.OK, listBooksResponseByGenre.getStatusCode());

		List<Book> listBooksByGenre =  listBooksResponse.getBody();
		log.info("listBooksByGenre: " + listBooksByGenre);
		assertTrue(listBooksByGenre.size()>1);


		//Comprovemq ue podem crear un nou llibre via PUT
		Optional<Genre> genre = genreRepository.findByName("Adventure");
		Book newBook = new Book("The Lion, the Witch and the Wardrobe","12345800", genre.get());

		//log.info("==============> newBook: " + newBook);
		restTemplate.put("http://localhost:" + this.port + "/rest/books/", newBook);

		List<Book> books = new ArrayList<Book>();
		(bookRepository.findAll()).forEach(books::add);

		log.info("==============> books.size(): " + books.size());

		assertTrue(books.size()==sizeBefore+1 ); 

	}

}
