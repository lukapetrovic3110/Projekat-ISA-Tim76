package Team76.InternetSoftwareArchitecture.model;

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
import javax.persistence.OneToOne;

@Entity
public class Ship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipId", unique = true, nullable = false)
	private Long shipId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "length", nullable = false)
	private Double length;

	@Column(name = "engineNumber", nullable = false)
	private Integer engineNumber;

	@Column(name = "enginePower", nullable = false)
	private Integer enginePower;

	@Column(name = "maxSpeed", nullable = false)
	private Integer maxSpeed;

	@Column(name = "capacity", nullable = false)
	private Integer capacity;

	@Enumerated(EnumType.STRING)
	private ShipType shipType;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<NavigationEquipment> navigationEquipment = new HashSet<NavigationEquipment>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "shipOwnerId", referencedColumnName = "userId")
	private ShipOwner shipOwner;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "priceListId", referencedColumnName = "priceListId")
	private PriceList priceList;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Rule> shipRules = new HashSet<Rule>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;

	public Ship() {
	}

	public Ship(String name, String description, Double rating, Double length, Integer engineNumber,
			Integer enginePower, Integer maxSpeed, Integer capacity, ShipType shipType,
			Set<NavigationEquipment> navigationEquipment, ShipOwner shipOwner, PriceList priceList, Set<Rule> shipRules,
			Address address) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.length = length;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.capacity = capacity;
		this.shipType = shipType;
		this.navigationEquipment = navigationEquipment;
		this.shipOwner = shipOwner;
		this.priceList = priceList;
		this.shipRules = shipRules;
		this.address = address;
	}

	public Long getShipId() {
		return shipId;
	}

	public void setShipId(Long shipId) {
		this.shipId = shipId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Integer getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(Integer engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
	}

	public Integer getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public Set<NavigationEquipment> getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(Set<NavigationEquipment> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public ShipOwner getShipOwner() {
		return shipOwner;
	}

	public void setShipOwner(ShipOwner shipOwner) {
		this.shipOwner = shipOwner;
	}

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public Set<Rule> getShipRules() {
		return shipRules;
	}

	public void setShipRules(Set<Rule> shipRules) {
		this.shipRules = shipRules;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
