package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriceTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "priceTagId", unique = true, nullable = false)
	private Long priceTagId;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "serviceName", nullable = false)
	private String serviceName;

	public PriceTag() {
	}

	public PriceTag(Double price, String serviceName) {
		super();
		this.price = price;
		this.serviceName = serviceName;
	}

	public Long getPriceTagId() {
		return priceTagId;
	}

	public void setPriceTagId(Long priceTagId) {
		this.priceTagId = priceTagId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
