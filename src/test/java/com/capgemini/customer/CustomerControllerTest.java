package com.capgemini.customer;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.controller.CustomerController;
import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerControllerTest {
	@Mock
	private CustomerService customerService;
	@InjectMocks
	private CustomerController customerController;
	private MockMvc mockmvc;
	Customer customer;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testaddCustomer() throws Exception {
		when(customerService.addCustomer(Mockito.isA(Customer.class)))
				.thenReturn(new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1"));
		mockmvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"customerId\":\"1234\",\"customerName\":\"swathi\",\"email\":\"swathi@gmail.com\",\"address\":\"hyderabad\",\"password\":\"swathi1\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.email").exists()).andExpect(jsonPath("$.address").exists())
				.andExpect(jsonPath("$.password").exists())

				.andExpect(jsonPath("$.customerId").value(1234)).andExpect(jsonPath("$.customerName").value("swathi"))
				.andExpect(jsonPath("$.email").value("swathi06@gmail.com"))
				.andExpect(jsonPath("$.address").value("hyderabad")).andExpect(jsonPath("$.password").value("swathi1"))
				.andDo(print());

	}

	@Test
	public void testupdateCustomer() throws Exception {
		when(customerService.updateCustomer(Mockito.isA(Customer.class)))
				.thenReturn(new Customer(1234, "swathi", "swathi31@gmail.com", "hyderabad", "swathi1"));
		/*when(customerService.getCustomerById(1234))
				.thenReturn(new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1"));*/
		mockmvc.perform(put("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"customerId\":\"1234\",\"customerName\":\"swathi\",\"email\":\"swathi31@gmail.com\",\"address\":\"hyderabad\",\"password\":\"swathi1\"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.email").exists()).andExpect(jsonPath("$.email").value("swathi31@gmail.com"))
				.andDo(print());

	}

	@Test
	public void testDelete() throws Exception {
		when(customerService.getCustomerById(1234))
				.thenReturn(new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1"));
		mockmvc.perform(delete("/customers/1234")).andExpect(status().isOk());

	}

	@Test
	public void testgetCustomerById() throws Exception {
		when(customerService.getCustomerById(1234))
				.thenReturn(new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1"));
		mockmvc.perform(MockMvcRequestBuilders.get("/customers/1234").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.email").exists()).andExpect(jsonPath("$.address").exists())
				.andExpect(jsonPath("$.password").exists())

				.andExpect(jsonPath("$.customerId").value(1234)).andExpect(jsonPath("$.customerName").value("swathi"))
				.andExpect(jsonPath("$.email").value("swathi06@gmail.com")).andExpect(jsonPath("$.address").value("hyderabad")).andExpect(jsonPath("$.password").value("swathi1"))
				.andDo(print());
	}

	@Test
	public void testAuthentication() throws Exception {
		Customer customer = new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1");
		when(customerService.authenticateCustomer(Mockito.isA(Integer.class), Mockito.isA(String.class)))
				.thenReturn(customer);
		mockmvc.perform(post("/customers/authen").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"customerId\":\"1234\",\"password\":\"swathi1\"}").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.password").exists())
				.andExpect(jsonPath("$.customerId").value(1234)).andExpect(jsonPath("$.password").value("swathi1"))
				.andDo(print());
	}
}
