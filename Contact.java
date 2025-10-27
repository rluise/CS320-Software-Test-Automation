package org.snhu.cs320.contact;

import org.snhu.cs320.validation.Validation;

public class Contact {
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	public Contact(String id, String firstName, String lastName, String phone, String address) throws Exception {
		super();
		
		setId(id);
		
		setFirstName(firstName);
		
		this.firstName = firstName;
		
		setLastName(lastName);
		
		this.lastName = lastName;
		
		setPhone(phone);
		
		this.phone = phone;
		
		setAddress(address);
		
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws Exception {		
		Validation.validateNotNull(firstName, "firstName");
		Validation.validateNotBlank(firstName, "firstName");
		Validation.validateLength(firstName, "firstName", 1, 10);
		
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception {
		Validation.validateNotNull(lastName, "lastName");
		Validation.validateNotBlank(lastName, "lastName");
		Validation.validateLength(lastName, "lastName", 1, 10);
		
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws Exception {
		Validation.validateNotNull(phone, "phone");
		Validation.validateNotBlank(phone, "phone");
		Validation.validateNumeric(phone, "phone");
		Validation.validateLength(phone, "phone", 1, 10);

		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws Exception {
		Validation.validateNotNull(address, "address");
		Validation.validateNotBlank(address, "address");
		Validation.validateAddressLength(address, "address", 1, 30);
		Validation.validatePunctuation(address, "address");
		
		this.address = address;
	}

	public String getId() {
		return id;
	}
	
	private void setId(String id) throws Exception {
		Validation.validateNotNull(id, "id");
		Validation.validateNotBlank(id, "id");
		Validation.validateLength(id, "id", 1, 10);
		
		this.id = id;
	}

}
