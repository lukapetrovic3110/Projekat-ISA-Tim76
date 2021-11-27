package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShipAdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipAdditionalServiceId", unique = true, nullable = false)
	private Long shipAdditionalServiceId;

	@Enumerated(EnumType.STRING)
	private ShipAdditionalServiceType shipAdditionalServiceType;

	public ShipAdditionalService() {
	}

	public ShipAdditionalService(ShipAdditionalServiceType shipAdditionalServiceType) {
		super();
		this.shipAdditionalServiceType = shipAdditionalServiceType;
	}

	public Long getShipAdditionalServiceId() {
		return shipAdditionalServiceId;
	}

	public void setShipAdditionalServiceId(Long shipAdditionalServiceId) {
		this.shipAdditionalServiceId = shipAdditionalServiceId;
	}

	public ShipAdditionalServiceType getShipAdditionalServiceType() {
		return shipAdditionalServiceType;
	}

	public void setShipAdditionalServiceType(ShipAdditionalServiceType shipAdditionalServiceType) {
		this.shipAdditionalServiceType = shipAdditionalServiceType;
	}

}
