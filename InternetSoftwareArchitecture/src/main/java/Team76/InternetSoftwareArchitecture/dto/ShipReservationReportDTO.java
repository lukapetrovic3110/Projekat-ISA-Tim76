package Team76.InternetSoftwareArchitecture.dto;

public class ShipReservationReportDTO {

	private String comment;
	private String clientArrival;
	private String clientEmail;
	private Long shipReservationId;
	
	public ShipReservationReportDTO() {
		
	}

	public ShipReservationReportDTO(String comment, String clientArrival, String clientEmail, Long shipReservationId) {
		super();
		this.comment = comment;
		this.clientArrival = clientArrival;
		this.clientEmail = clientEmail;
		this.shipReservationId = shipReservationId;
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

	public Long getShipReservationId() {
		return shipReservationId;
	}

	public void setShipReservationId(Long shipReservationId) {
		this.shipReservationId = shipReservationId;
	}
	
}
