package com.capgemini.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exceptions.CustomerNotFoundException;
import com.capgemini.customer.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer),
				HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {

		try {
			Customer customerFromDb = customerService.getCustomerById(customer.getCustomerId());
			if (customerFromDb != null) {
				return new ResponseEntity<Customer>(customerService.updateCustomer(customerFromDb), HttpStatus.OK);
			}
		} catch (CustomerNotFoundException exception) {

		}

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) throws CustomerNotFoundException {
		Customer customerFromDb = customerService.getCustomerById(customerId);
		return new ResponseEntity<Customer>(customerFromDb, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
		try {
			Customer c = customerService.getCustomerById(customerId);
			if (c != null) {
				customerService.deleteCustomer(customerId);
			return new ResponseEntity<Customer>(HttpStatus.OK);
			}
		} catch (CustomerNotFoundException exception) {

		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
@GetMapping("/customers/{customerId}/{password}")
	public ResponseEntity<Customer> authenticateCustomer(@PathVariable int customerId, String password)
			throws AuthenticationFailedException {
		Customer cus = customerService.authenticateCustomer(customerId, password);

		return new ResponseEntity<Customer>(HttpStatus.OK);

	}
}