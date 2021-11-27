package Team76.InternetSoftwareArchitecture.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cottage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cottageId", unique = true, nullable = false)
	private Long cottageId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "nanumberOfRoomsme", nullable = false)
	private Integer numberOfRooms;

	@Column(name = "numberOfBedsPerRoom", nullable = false)
	private Integer numberOfBedsPerRoom;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cottageOwnerId", referencedColumnName = "userId")
	private CottageOwner cottageOwner;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "priceListId", referencedColumnName = "priceListId")
	private PriceList priceList;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Rule> cottageRules = new HashSet<Rule>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;

	public Cottage() {
	}

	public Cottage(String name, String description, Double rating, Integer numberOfRooms, Integer numberOfBedsPerRoom,
			CottageOwner cottageOwner, PriceList priceList, Set<Rule> cottageRules, Address address) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
		this.cottageOwner = cottageOwner;
		this.priceList = priceList;
		this.cottageRules = cottageRules;
		this.address = address;
	}

	public Long getCottageId() {
		return cottageId;
	}

	public void setCottageId(Long cottageId) {
		this.cottageId = cottageId;
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

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getNumberOfBedsPerRoom() {
		return numberOfBedsPerRoom;
	}

	public void setNumberOfBedsPerRoom(Integer numberOfBedsPerRoom) {
		this.numberOfBedsPerRoom = numberOfBedsPerRoom;
	}

	public CottageOwner getCottageOwner() {
		return cottageOwner;
	}

	public void setCottageOwner(CottageOwner cottageOwner) {
		this.cottageOwner = cottageOwner;
	}

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public Set<Rule> getCottageRules() {
		return cottageRules;
	}

	public void setCottageRules(Set<Rule> cottageRules) {
		this.cottageRules = cottageRules;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
