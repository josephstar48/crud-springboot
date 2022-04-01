package com.springcheckpoint.springcheckpoint;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc
@SpringBootTest
class SpringcheckpointApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	@Transactional
	@Rollback
	void testPostMappingCalculate() throws Exception {

		//Get the lesson 5 value
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
	void testPostForOneUser() throws Exception {

		//Get the lesson 5 value
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
	void testGetByPath() throws Exception {

		//Get the lesson 5 value
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
	void testGetAndPatch() throws Exception {

		//Get the lesson 5 value
		this.mvc.perform(patch("/users/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"john@example.com\", \"password\": \"1234\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("5"))
				.andExpect(jsonPath("$.email").value("john@example.com")
				);


	}

}
