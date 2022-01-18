package Team76.InternetSoftwareArchitecture.dto;

public class CottageReservationReportDTO {
	
	private String comment;
	private String clientArrival;
	private String clientEmail;
	private Long cottageReservationId;
	
	public CottageReservationReportDTO() {
		
	}

	public CottageReservationReportDTO(String comment, String clientArrival, String clientEmail,
			Long cottageReservationId) {
		super();
		this.comment = comment;
		this.clientArrival = clientArrival;
		this.clientEmail = clientEmail;
		this.cottageReservationId = cottageReservationId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClientArrival() {
		return clientArrival;
	}

	public void setClientArrival(String clientArrival) {
		this.clientArrival = clientArrival;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Long getCottageReservationId() {
		return cottageReservationId;
	}

	public void setCottageReservationId(Long cottageReservationId) {
		this.cottageReservationId = cottageReservationId;
	}
	
}
