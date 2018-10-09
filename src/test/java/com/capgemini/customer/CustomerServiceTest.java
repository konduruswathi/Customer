package com.capgemini.customer;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.CustomerService;
import com.capgemini.customer.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	private MockMvc mockmvc;
	Customer customer;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(customerServiceImpl).build();
	}

	@Test
	public void testAuthenticateCustomer() throws Exception {
Customer c=new Customer(1234,"swathi","swathi06@gmail.com","hyderabad","swathi1");
		Optional<Customer> optionalCustomer = Optional.of(c);
				
when(customerRepository.findById(1234)).thenReturn(optionalCustomer);
assertEquals(customerServiceImpl.authenticateCustomer(1234,"swathi1"),c);
	}
}

	/*@Test
	public void testaddCustomer() throws Exception {
		Customer cus = new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1");
		when(customerRepository.save(cus)).thenReturn(cus);
		assertEquals(customerServiceImpl.addCustomer(cus), cus);
	}

	@Test
	public void testupdateCustomer() throws Exception {
		Customer cus1 = new Customer(1234, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1");
		Customer cus2 = new Customer(1234, "swathi", "swathi31@gmail.com", "hyderabad", "swathi1");
		when(customerRepository.save(cus1)).thenReturn(cus2);
		assertEquals(customerServiceImpl.updateCustomer(cus1), cus2);
	}

	@Test
	public void testgetCustomerById() throws Exception {
		Customer cus3 = new Customer(123, "swathi", "swathi06@gmail.com", "hyderabad", "swathi1");
		Optional<Customer> customer = Optional.of(cus3);
		when(customerRepository.findById(1234)).thenReturn(customer);
		assertEquals(customerServiceImpl.getCustomerById(1234), cus3);

	}

	@Test
	public void deleteProduct() throws Exception {
		customerServiceImpl.deleteCustomer(1234);
		verify(customerRepository, times(1)).deleteById(1234);
	}
}
*/