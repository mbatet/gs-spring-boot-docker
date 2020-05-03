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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class HelloWorldConfigurationTests {

	private static final Logger log = LoggerFactory.getLogger(HelloWorldConfigurationTests.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AdminService adminService;

	@Test
	public void testGreeting() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testInserts() throws Exception {


		adminService.deleteData(); //Per si de cas, per no tenir problemes insertant

		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/admin/insertData", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testRetrieve() throws Exception {

		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/admin/retrieveData", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}


	/*
	*     @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    @RequestMapping(value = "/genre/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@NotNull String genrename) {
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book put(@NotNull Book book, BindingResult result) {
    * */

	@Test
	public void testBookController() throws Exception {


		adminService.deleteData(); //Per si de cas, per no tenir problemes insertant
		adminService.insertData(); //Per posar unes cuantes dades a recuperar


		//Comprovem que podem recuperar un llibre
		ResponseEntity<Book> bookResponse = restTemplate.getForEntity("http://localhost:" + this.port + "/books/11", Book.class);
		log.info("bookResponse" + bookResponse);
		assertEquals(HttpStatus.OK, bookResponse.getStatusCode());


		Book book = bookResponse.getBody();
		assertEquals(book.getId(), Long.valueOf(11));


		//Comprovem que podem recuperar un llistat de llibres
		ResponseEntity<List> listBooksResponse = restTemplate.getForEntity("http://localhost:" + this.port + "/books/", List.class);
		assertEquals(HttpStatus.OK, listBooksResponse.getStatusCode());

		List<Book> listBooks =  listBooksResponse.getBody();
		log.info("listBooks" + listBooks);
		assertTrue(listBooks.size()>1);


		//Comprovem que podem recuperar un llistat de llibres per genere
		ResponseEntity<List> listBooksResponseByGenre = restTemplate.getForEntity("http://localhost:" + this.port + "/books/genre/Scy-fi", List.class);
		assertEquals(HttpStatus.OK, listBooksResponseByGenre.getStatusCode());

		List<Book> listBooksByGenre =  listBooksResponse.getBody();
		log.info("listBooksByGenre" + listBooksByGenre);
		assertTrue(listBooksByGenre.size()>1);

	}

}
