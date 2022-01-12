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
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reportId", unique = true, nullable = false)
	private Long reportId;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Column(name = "clientArrival", nullable = false)
	private Boolean clientArrival;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservationCottageId", referencedColumnName = "reservationCottageId", nullable = true)
	private ReservationCottage reservationCottage;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservationShipId", referencedColumnName = "reservationShipId", nullable = true)
	private ReservationShip reservationShip;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "reservationAdventureId", referencedColumnName = "reservationAdventureId", nullable = true)
	private ReservationAdventure reservationAdventure;
	
	public Report() {
		
	}

	public Report(String comment, ReservationCottage reservationCottage, ReservationShip reservationShip,
			ReservationAdventure reservationAdventure) {
		super();
		this.comment = comment;
		this.reservationCottage = reservationCottage;
		this.reservationShip = reservationShip;
		this.reservationAdventure = reservationAdventure;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ReservationCottage getReservationCottage() {
		return reservationCottage;
	}

	public void setReservationCottage(ReservationCottage reservationCottage) {
		this.reservationCottage = reservationCottage;
	}

	public ReservationShip getReservationShip() {
		return reservationShip;
	}

	public void setReservationShip(ReservationShip reservationShip) {
		this.reservationShip = reservationShip;
	}

	public ReservationAdventure getReservationAdventure() {
		return reservationAdventure;
	}

	public void setReservationAdventure(ReservationAdventure reservationAdventure) {
		this.reservationAdventure = reservationAdventure;
	}
	
}
