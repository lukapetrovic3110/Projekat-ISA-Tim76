package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CottageReservationReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cottageReservationReportId", unique = true, nullable = false)
	private Long cottageReservationReportId;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Column(name = "clientArrived", nullable = false)
	private Boolean clientArrived;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservationCottageId", referencedColumnName = "reservationCottageId", nullable = true)
	private ReservationCottage reservationCottage;

	public CottageReservationReport(String comment, Boolean clientArrived, ReservationCottage reservationCottage) {
		super();
		this.comment = comment;
		this.clientArrived = clientArrived;
		this.reservationCottage = reservationCottage;
	}

	public Long getCottageReservationReportId() {
		return cottageReservationReportId;
	}

	public void setCottageReservationReportId(Long cottageReservationReportId) {
		this.cottageReservationReportId = cottageReservationReportId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getClientArrived() {
		return clientArrived;
	}

	public void setClientArrived(Boolean clientArrived) {
		this.clientArrived = clientArrived;
	}

	public ReservationCottage getReservationCottage() {
		return reservationCottage;
	}

	public void setReservationCottage(ReservationCottage reservationCottage) {
		this.reservationCottage = reservationCottage;
	}
		
}
