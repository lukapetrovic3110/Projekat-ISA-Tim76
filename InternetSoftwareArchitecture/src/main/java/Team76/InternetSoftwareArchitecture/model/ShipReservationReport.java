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
public class ShipReservationReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipReservationReportId", unique = true, nullable = false)
	private Long shipReservationReportId;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Column(name = "clientArrived", nullable = false)
	private Boolean clientArrived;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservationShipId", referencedColumnName = "reservationShipId", nullable = true)
	private ReservationShip reservationShip;

	public ShipReservationReport(String comment, Boolean clientArrived, ReservationShip reservationShip) {
		super();
		this.comment = comment;
		this.clientArrived = clientArrived;
		this.reservationShip = reservationShip;
	}

	public Long getShipReservationReportId() {
		return shipReservationReportId;
	}

	public void setShipReservationReportId(Long shipReservationReportId) {
		this.shipReservationReportId = shipReservationReportId;
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

	public ReservationShip getReservationShip() {
		return reservationShip;
	}

	public void setReservationShip(ReservationShip reservationShip) {
		this.reservationShip = reservationShip;
	}
	
}
