package Team76.InternetSoftwareArchitecture.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ReservationShip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationShipId", unique = true, nullable = false)
	private Long reservationShipId;

	@Column(name = "dateAndTime", nullable = false)
	private Date dateAndTime;

	@Column(name = "duration", nullable = false)
	private Integer duration;

	@Column(name = "maxNumberOfPersons", nullable = false)
	private Integer maxNumberOfPersons;

	@Column(name = "price", nullable = false)
	private Double price;

	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;

	@Enumerated(EnumType.STRING)
	private ShipReservationCancelTerm shipReservationCancelTerm;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "shipId", referencedColumnName = "shipId")
	private Ship ship;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId", referencedColumnName = "userId")
	private Client client;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ShipAdditionalService> shipAdditionalServices = new HashSet<ShipAdditionalService>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FishingEquipmentForShip> fishingEquipmentForShip = new HashSet<FishingEquipmentForShip>();

	public ReservationShip() {
	}

	public ReservationShip(Date dateAndTime, Integer duration, Integer maxNumberOfPersons, Double price,
			ReservationStatus reservationStatus, ShipReservationCancelTerm shipReservationCancelTerm, Ship ship,
			Client client, Set<ShipAdditionalService> shipAdditionalServices,
			Set<FishingEquipmentForShip> fishingEquipmentForShip) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.price = price;
		this.reservationStatus = reservationStatus;
		this.shipReservationCancelTerm = shipReservationCancelTerm;
		this.ship = ship;
		this.client = client;
		this.shipAdditionalServices = shipAdditionalServices;
		this.fishingEquipmentForShip = fishingEquipmentForShip;
	}

	public Long getReservationShipId() {
		return reservationShipId;
	}

	public void setReservationShipId(Long reservationShipId) {
		this.reservationShipId = reservationShipId;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public ShipReservationCancelTerm getShipReservationCancelTerm() {
		return shipReservationCancelTerm;
	}

	public void setShipReservationCancelTerm(ShipReservationCancelTerm shipReservationCancelTerm) {
		this.shipReservationCancelTerm = shipReservationCancelTerm;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<ShipAdditionalService> getShipAdditionalServices() {
		return shipAdditionalServices;
	}

	public void setShipAdditionalServices(Set<ShipAdditionalService> shipAdditionalServices) {
		this.shipAdditionalServices = shipAdditionalServices;
	}

	public Set<FishingEquipmentForShip> getFishingEquipmentForShip() {
		return fishingEquipmentForShip;
	}

	public void setFishingEquipmentForShip(Set<FishingEquipmentForShip> fishingEquipmentForShip) {
		this.fishingEquipmentForShip = fishingEquipmentForShip;
	}

}
