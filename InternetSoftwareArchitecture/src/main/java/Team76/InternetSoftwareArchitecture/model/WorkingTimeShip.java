package Team76.InternetSoftwareArchitecture.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class WorkingTimeShip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workingTimeShipId", unique = true, nullable = false)
	private Long workingTimeShipId;

	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "shipId", referencedColumnName = "shipId")
	private Ship ship;

	public WorkingTimeShip() { }

	public WorkingTimeShip(Date startDate, Date endDate, Ship ship) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.ship = ship;
	}

	public Long getWorkingTimeShipId() {
		return workingTimeShipId;
	}

	public void setWorkingTimeShipId(Long workingTimeShipId) {
		this.workingTimeShipId = workingTimeShipId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
}
