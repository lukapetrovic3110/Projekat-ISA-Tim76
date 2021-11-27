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
public class ReservationCottage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationCottageId", unique = true, nullable = false)
	private Long reservationCottageId;

	@Column(name = "dateAndTime", nullable = false)
	private Date dateAndTime;

	@Column(name = "duration", nullable = false)
	private Integer duration;

	@Column(name = "maxNumberOfPersons", nullable = false)
	private Integer maxNumberOfPersons;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cottageId", referencedColumnName = "cottageId")
	private Cottage cottage;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId", referencedColumnName = "userId")
	private Client client;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CottageAdditionalService> cottageAdditionalServices = new HashSet<CottageAdditionalService>();

	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;

	public ReservationCottage() {
	}

	public ReservationCottage(Date dateAndTime, Integer duration, Integer maxNumberOfPersons, Double price,
			Cottage cottage, Client client, Set<CottageAdditionalService> cottageAdditionalServices,
			ReservationStatus reservationStatus) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.price = price;
		this.cottage = cottage;
		this.client = client;
		this.cottageAdditionalServices = cottageAdditionalServices;
		this.reservationStatus = reservationStatus;
	}

	public Long getReservationCottageId() {
		return reservationCottageId;
	}

	public void setReservationCottageId(Long reservationCottageId) {
		this.reservationCottageId = reservationCottageId;
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

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<CottageAdditionalService> getCottageAdditionalServices() {
		return cottageAdditionalServices;
	}

	public void setCottageAdditionalServices(Set<CottageAdditionalService> cottageAdditionalServices) {
		this.cottageAdditionalServices = cottageAdditionalServices;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

}
