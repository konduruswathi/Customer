package com.capgemini.customer.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	@Id
private int customerId;
private String customerName;
private String email;
private String address;
private String password;
public Customer() {
	super();
	
}
public Customer(int customerId, String customerName, String email, String address, String password) {
	super();
	this.customerId = customerId;
	this.customerName = customerName;
	this.email = email;
	this.address = address;
	this.password = password;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email + ", address="
			+ address + ", password=" + password + "]";
}

}
