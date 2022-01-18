package Team76.InternetSoftwareArchitecture.dto;

import Team76.InternetSoftwareArchitecture.model.Address;

public class CottageReservationClientInformationDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Address address;
	
	public CottageReservationClientInformationDTO() {
		
	}

	public CottageReservationClientInformationDTO(String firstName, String lastName, String email, String phoneNumber,
			Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
