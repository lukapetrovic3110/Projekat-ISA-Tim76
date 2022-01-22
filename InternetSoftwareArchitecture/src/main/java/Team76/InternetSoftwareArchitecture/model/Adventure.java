package Team76.InternetSoftwareArchitecture.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

	@Column(name = "availabilityStart", nullable = false)
	private Date availabilityStart;

	@Column(name = "availabilityEnd", nullable = false)
	private Date availabilityEnd;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fishingInstructorId", referencedColumnName = "userId")
	private FishingInstructor fishingInstructor;
	
	@Column(name = "pricePerHour", nullable = false)	
	private Double pricePerHour;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "priceListId", referencedColumnName = "priceListId")
	private PriceList priceList;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Rule> adventureRules = new HashSet<Rule>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Image> images;
	
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	public Adventure() {
	}
	
	public Adventure(String name, String description, Double rating, String instructorBiography,
			Integer maxNumberOfPersons, Date availabilityStart, Date availabilityEnd,
			FishingInstructor fishingInstructor, Double pricePerHour, PriceList priceList, Set<Rule> adventureRules,
			Address address, List<Image> images, EntityStatus status) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.instructorBiography = instructorBiography;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.availabilityStart = availabilityStart;
		this.availabilityEnd = availabilityEnd;
		this.fishingInstructor = fishingInstructor;
		this.pricePerHour = pricePerHour;
		this.priceList = priceList;
		this.adventureRules = adventureRules;
		this.address = address;
		this.images = images;
		this.status = status;
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
	
	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
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

	public Date getAvailabilityStart() {
		return availabilityStart;
	}

	public void setAvailabilityStart(Date availabilityStart) {
		this.availabilityStart = availabilityStart;
	}

	public Date getAvailabilityEnd() {
		return availabilityEnd;
	}

	public void setAvailabilityEnd(Date availabilityEnd) {
		this.availabilityEnd = availabilityEnd;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public EntityStatus getStatus() {
		return status;
	}

	public void setStatus(EntityStatus status) {
		this.status = status;
	}
	
}
