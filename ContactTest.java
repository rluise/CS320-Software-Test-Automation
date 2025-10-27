package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContactTest {
	@Test
	void testSuccessfulCreation() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThat(contact)
		.hasFieldOrPropertyWithValue("id", "1")
		.hasFieldOrPropertyWithValue("firstName", "First")
		.hasFieldOrPropertyWithValue("lastName", "Last")
		.hasFieldOrPropertyWithValue("phone", "5553334444")
		.hasFieldOrPropertyWithValue("address", "1234 Loblolly Lane");
		
	}
	
	@Test
	void testSuccessfulSetters() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		contact.setFirstName("Luis");
		contact.setLastName("Rodriguez");
		contact.setPhone("9991112222");
		contact.setAddress("158 Lake Rebecca");
		assertThat(contact)
		.hasFieldOrPropertyWithValue("id", "1")
		.hasFieldOrPropertyWithValue("firstName", "Luis")
		.hasFieldOrPropertyWithValue("lastName", "Rodriguez")
		.hasFieldOrPropertyWithValue("phone", "9991112222")
		.hasFieldOrPropertyWithValue("address", "158 Lake Rebecca");
	}
	
	@CsvSource({
		"' ',First,Last,5553334444,1234 Loblolly Lane,id must not be blank", //Blank ID
		",First,Last,5553334444,1234 Loblolly Lane,id must not be null", //Null ID
		"12345678901,First,Last,5553334444,1234 Loblolly Lane,id must be at least 1 and no greater than 10 characters in length", //Too long ID
		"12345,' ',Last,5553334444,1234 Loblolly Lane,firstName must not be blank", //Blank First Name
		"12345,,Last,5553334444,1234 Loblolly Lane,firstName must not be null", //Null First Name
		"12345,FirstFirstF,Last,5553334444,1234 Loblolly Lane,firstName must be at least 1 and no greater than 10 characters in length", //Too long First Name
		"12345,First,' ',5553334444,1234 Loblolly Lane,lastName must not be blank", //Blank Last Name
		"12345,First,,5553334444,1234 Loblolly Lane,lastName must not be null", //Null Last Name
		"12345,First,LastLastLastLastL,5553334444,1234 Loblolly Lane, lastName must be at least 1 and no greater than 10 characters in length", //Too long Last Name
		"12345,First,Last,55533344449,1234 Loblolly Lane,phone must be at least 1 and no greater than 10 characters in length", //Too Long Phone
		"12345,First,Last,5553334444A,1234 Loblolly Lane,phone must only contain digits", //Phone With Letters
		"12345,First,Last,555333-4444,1234 Loblolly Lane,phone must only contain digits", //Phone With Punctuation
		"12345,First,Last,555 3334444,1234 Loblolly Lane,phone must only contain digits", //Phone With Spaces
		"12345,First,Last,5553334444,' ',address must not be blank", //Blank address
		"12345,First,Last,5553334444,,address must not be null", //Null address
		"12345,First,Last,5553334444,1234 Loblolly Lane Canyon Drive Road,address must be at least 1 and no greater than 30 characters in length", //Too long address
		"12345,First,Last,5553334444,1234 Loblolly Lane!,address must not contain punctuation marks", //Address With Punctuation
	
	})
	@ParameterizedTest
	void testFailedCreation(String id, String firstName, String lastName, String phone, String address, String message) {
		assertThatThrownBy(() -> new Contact(id, firstName, lastName, phone, address))
			.isNotNull()
			.hasMessage(message);
	}
	
	@CsvSource({
		",firstName must not be null",
		"' ',firstName must not be blank",
		"FirstNameFirstName,firstName must be at least 1 and no greater than 10 characters in length"
	})
	
	@ParameterizedTest
	void testSettingFirstName(String firstName, String message) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThatThrownBy(() -> contact.setFirstName(firstName))
		.isNotNull()
		.hasMessage(message);
		
	}
	
	@CsvSource({
		",lastName must not be null",
		"' ',lastName must not be blank",
		"LastNameLastName,lastName must be at least 1 and no greater than 10 characters in length"
	})
	
	@ParameterizedTest
	void testSettingLasttName(String lastName, String message) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThatThrownBy(() -> contact.setLastName(lastName))
		.isNotNull()
		.hasMessage(message);
		
	}
	
	@CsvSource({
		",phone must not be null",
		"' ',phone must not be blank",
		"5553334444A, phone must only contain digits",
		"555333-4444, phone must only contain digits",
		"555 3334444, phone must only contain digits",
		"55533344449,phone must be at least 1 and no greater than 10 characters in length"
	})
	
	@ParameterizedTest
	void testSettingPhone(String phone, String message) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThatThrownBy(() -> contact.setPhone(phone))
		.isNotNull()
		.hasMessage(message);
		
	}
	
	@CsvSource({
		",address must not be null",
		"' ',address must not be blank",
		"1234 Loblolly Lane Canyon Drive Road, address must be at least 1 and no greater than 30 characters in length",
		"1234 Loblolly Lane!,address must not contain punctuation marks"
	})
	
	@ParameterizedTest
	void testSettingAddress(String address, String message) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThatThrownBy(() -> contact.setAddress(address))
		.isNotNull()
		.hasMessage(message);
		
	}
	
	
}
