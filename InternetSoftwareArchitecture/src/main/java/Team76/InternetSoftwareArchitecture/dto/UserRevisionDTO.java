package Team76.InternetSoftwareArchitecture.dto;

public class UserRevisionDTO {
	
	private Long revisionId;
	private String firstName;
	private String lastName;
	private String email;
	private String comment;
	private Double rating;
	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	
	public UserRevisionDTO() {
		
	}

	public UserRevisionDTO(Long revisionId, String firstName, String lastName, String email, String comment,
			Double rating, String clientFirstName, String clientLastName, String clientEmail) {
		super();
		this.revisionId = revisionId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.comment = comment;
		this.rating = rating;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		this.clientEmail = clientEmail;
	}

	public Long getRevisionId() {
		return revisionId;
	}

	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}


}
