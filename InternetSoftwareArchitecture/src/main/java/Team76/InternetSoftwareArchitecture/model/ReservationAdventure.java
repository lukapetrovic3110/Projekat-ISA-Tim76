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
public class ReservationAdventure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationAdventureId", unique = true, nullable = false)
	private Long reservationAdventureId;

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
	private AdventureReservationCancelTerm adventureReservationCancelTerm;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "adventureId", referencedColumnName = "adventureId")
	private Adventure adventure;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId", referencedColumnName = "userId")
	private Client client;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AdventureAdditionalService> adventureAdditionalService = new HashSet<AdventureAdditionalService>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FishingEquipmentForAdventure> fishingEquipmentForAdventure = new HashSet<FishingEquipmentForAdventure>();

	public ReservationAdventure() {

	}

	public ReservationAdventure(Date dateAndTime, Integer duration, Integer maxNumberOfPersons, Double price,
			ReservationStatus reservationStatus, AdventureReservationCancelTerm adventureReservationCancelTerm,
			Adventure adventure, Client client, Set<AdventureAdditionalService> adventureAdditionalService,
			Set<FishingEquipmentForAdventure> fishingEquipmentForAdventure) {
		super();
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.price = price;
		this.reservationStatus = reservationStatus;
		this.adventureReservationCancelTerm = adventureReservationCancelTerm;
		this.adventure = adventure;
		this.client = client;
		this.adventureAdditionalService = adventureAdditionalService;
		this.fishingEquipmentForAdventure = fishingEquipmentForAdventure;
	}

	public Long getReservationAdventureId() {
		return reservationAdventureId;
	}

	public void setReservationAdventureId(Long reservationAdventureId) {
		this.reservationAdventureId = reservationAdventureId;
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

	public AdventureReservationCancelTerm getAdventureReservationCancelTerm() {
		return adventureReservationCancelTerm;
	}

	public void setAdventureReservationCancelTerm(AdventureReservationCancelTerm adventureReservationCancelTerm) {
		this.adventureReservationCancelTerm = adventureReservationCancelTerm;
	}

	public Adventure getAdventure() {
		return adventure;
	}

	public void setAdventure(Adventure adventure) {
		this.adventure = adventure;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<AdventureAdditionalService> getAdventureAdditionalService() {
		return adventureAdditionalService;
	}

	public void setAdventureAdditionalService(Set<AdventureAdditionalService> adventureAdditionalService) {
		this.adventureAdditionalService = adventureAdditionalService;
	}

	public Set<FishingEquipmentForAdventure> getFishingEquipmentForAdventure() {
		return fishingEquipmentForAdventure;
	}

	public void setFishingEquipmentForAdventure(Set<FishingEquipmentForAdventure> fishingEquipmentForAdventure) {
		this.fishingEquipmentForAdventure = fishingEquipmentForAdventure;
	}

}
