package com.springcheckpoint.springcheckpoint;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc
@SpringBootTest
class SpringCheckpointApplicationTests {

	@Autowired
	MockMvc mvc;

	@Mock
	private UserRepository userRepository;


	@Test
	@Transactional
	@Rollback
	void canGetAListOfUsers() throws Exception {

		this.mvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email").value("josh@joshmatos.com") );


		this.mvc.perform(get("/users")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].email").value("jose@jose.com") );


		//Delete the lesson 5
//		MockHttpServletRequestBuilder requestBuilder2 = delete("/lessons/5");
//
//		this.mvc.perform(requestBuilder2)
//				.andExpect(status().isOk())
//				.andExpect(content().string("The ID 5 has been deleted from the database"));
	}
	@Test
	@Transactional
	@Rollback
	void canCreateAUserWithPost() throws Exception {


		this.mvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"john@example.com\", \"password\": \"something-secret\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.email").value("john@example.com")
				);
	}

	@Test
	@Transactional
	@Rollback
	void canGetAUserWithPathVariable() throws Exception {


		this.mvc.perform(get("/users/2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.email").value("jose@jose.com")
				);
	}

	@Test
	@Transactional
	@Rollback
	void canUpdateAUserWithPatchAndPathVariable() throws Exception {


		this.mvc.perform(patch("/users/5")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"john@example.com\", \"password\": \"1234\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("5"))
				.andExpect(jsonPath("$.email").value("john@example.com")
				);


	}
	@Test
	@Transactional
	@Rollback
	void canDeleteUserAndReturnCount() throws Exception {
		this.mvc.perform(delete(("/users/1")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.count").value("4"));
	}

	@Test
	@Transactional
	@Rollback
	void canPostAndAuthenticateUser() throws Exception {
		User user6 = new User("josh@joshmatos.com", "password");
		userRepository.save(user6);
		this.mvc.perform(post("/users/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"josh@joshmatos.com\", \"password\": \"password\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.authenticated").value(true))
				.andExpect(jsonPath("$.user.id").value("1"))
				.andExpect(jsonPath("$.user.email").value("josh@joshmatos.com"));

	}

}

//{
//  "authenticated": true,
//  "user": {
//    "id": 12,
//    "email": "angelica@example.com"
//  }
//}
