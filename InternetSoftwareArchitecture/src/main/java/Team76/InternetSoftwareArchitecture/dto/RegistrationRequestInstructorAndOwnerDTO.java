package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.Address;

public class RegistrationRequestInstructorAndOwnerDTO {
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Address address;
	
	private String phoneNumber;
	
	private String explanation;
	
	public RegistrationRequestInstructorAndOwnerDTO() {
		
	}

	public RegistrationRequestInstructorAndOwnerDTO(String email, String password, String firstName, String lastName,
			Address address, String phoneNumber, String explanation) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.explanation = explanation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
		
}
