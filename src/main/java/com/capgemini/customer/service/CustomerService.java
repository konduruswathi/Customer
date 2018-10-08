package com.capgemini.customer.service;

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerNotFoundException;

public interface CustomerService {
public Customer authenticateCustomer(int cusomerId,String password) throws AuthenticationFailedException;
public Customer addCustomer(Customer customer);
public Customer updateCustomer(Customer customer);
public Customer getCustomerById(int customerId)throws CustomerNotFoundException;
public void deleteCustomer(int customerId);
}
