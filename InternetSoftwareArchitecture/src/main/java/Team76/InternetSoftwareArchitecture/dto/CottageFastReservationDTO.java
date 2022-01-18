package Team76.InternetSoftwareArchitecture.dto;

import java.util.Date;
import java.util.Set;

import Team76.InternetSoftwareArchitecture.model.CottageAdditionalService;

public class CottageFastReservationDTO {
	
	private Long cottageFastReservationId;
	private Date dateAndTime;
	private Integer duration;
	private Integer maxNumberOfPersons;
	private Set<CottageAdditionalService> cottageAdditionalServices;
	private Double price;
	private Integer discountPercentage;
	
	public CottageFastReservationDTO() {
		
	}

	public CottageFastReservationDTO(Long cottageFastReservationId, Date dateAndTime, Integer duration,
			Integer maxNumberOfPersons, Set<CottageAdditionalService> cottageAdditionalServices, Double price,
			Integer discountPercentage) {
		super();
		this.cottageFastReservationId = cottageFastReservationId;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.maxNumberOfPersons = maxNumberOfPersons;
		this.cottageAdditionalServices = cottageAdditionalServices;
		this.price = price;
		this.discountPercentage = discountPercentage;
	}

	public Long getCottageFastReservationId() {
		return cottageFastReservationId;
	}

	public void setCottageFastReservationId(Long cottageFastReservationId) {
		this.cottageFastReservationId = cottageFastReservationId;
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

	public Set<CottageAdditionalService> getCottageAdditionalServices() {
		return cottageAdditionalServices;
	}

	public void setCottageAdditionalServices(Set<CottageAdditionalService> cottageAdditionalServices) {
		this.cottageAdditionalServices = cottageAdditionalServices;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

}
