package Team76.InternetSoftwareArchitecture.dto;

public class ObjectRevisionDTO {
	
	private Long revisionId;
	private String name;
	private String comment;
	private Double rating;
	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	
	public ObjectRevisionDTO() {
		
	}
	
	public ObjectRevisionDTO(Long revisionId, String name, String comment, Double rating, String clientFirstName,
			String clientLastName, String clientEmail, String ownerFirstName, String ownerLastName, String ownerEmail) {
		super();
		this.revisionId = revisionId;
		this.name = name;
		this.comment = comment;
		this.rating = rating;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		this.clientEmail = clientEmail;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
	}



	public Long getRevisionId() {
		return revisionId;
	}

	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
}
