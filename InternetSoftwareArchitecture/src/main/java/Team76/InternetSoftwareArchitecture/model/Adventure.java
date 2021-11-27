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
public class Adventure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adventureId", unique = true, nullable = false)
	private Long adventureId;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "rating")
	private Double rating;
	
	@Column(name = "instructorBiography", nullable = false)
	private String instructorBiography;
	
	@Column(name = "maxNumberOfPersons", nullable = false)
	private Integer maxNumberOfPersons;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fishingInstructorId", referencedColumnName = "fishingInstructorId")
	private FishingInstructor fishingInstructor;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "priceListId", referencedColumnName = "priceListId")
	private PriceList priceList;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Rule> adventureRules = new HashSet<Rule>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;
	
	public Adventure() { }

	public Adventure(String name, String description, Double rating, String instructorBiography,
			Integer maxNumberOfPersons, FishingInstructor fishingInstructor, PriceList priceList,
			Set<Rule> adventureRules, Address address) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.instructorBiography = instructorBiography;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.fishingInstructor = fishingInstructor;
		this.priceList = priceList;
		this.adventureRules = adventureRules;
		this.address = address;
	}

	public Long getAdventureId() {
		return adventureId;
	}

	public void setAdventureId(Long adventureId) {
		this.adventureId = adventureId;
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

	public String getInstructorBiography() {
		return instructorBiography;
	}

	public void setInstructorBiography(String instructorBiography) {
		this.instructorBiography = instructorBiography;
	}

	public Integer getMaxNumberOfPersons() {
		return maxNumberOfPersons;
	}

	public void setMaxNumberOfPersons(Integer maxNumberOfPersons) {
		this.maxNumberOfPersons = maxNumberOfPersons;
	}

	public FishingInstructor getFishingInstructor() {
		return fishingInstructor;
	}

	public void setFishingInstructor(FishingInstructor fishingInstructor) {
		this.fishingInstructor = fishingInstructor;
	}

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public Set<Rule> getAdventureRules() {
		return adventureRules;
	}

	public void setAdventureRules(Set<Rule> adventureRules) {
		this.adventureRules = adventureRules;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
