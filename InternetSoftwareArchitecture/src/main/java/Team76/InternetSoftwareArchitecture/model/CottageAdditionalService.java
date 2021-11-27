package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CottageAdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cottageAdditionalServiceId", unique = true, nullable = false)
	private Long cottageAdditionalServiceId;

	@Enumerated(EnumType.STRING)
	private CottageAdditionalServiceType cottageAdditionalServiceType;

	public CottageAdditionalService() {
	}

	public CottageAdditionalService(CottageAdditionalServiceType cottageAdditionalServiceType) {
		super();
		this.cottageAdditionalServiceType = cottageAdditionalServiceType;
	}

	public Long getCottageAdditionalServiceId() {
		return cottageAdditionalServiceId;
	}

	public void setCottageAdditionalServiceId(Long cottageAdditionalServiceId) {
		this.cottageAdditionalServiceId = cottageAdditionalServiceId;
	}

	public CottageAdditionalServiceType getCottageAdditionalServiceType() {
		return cottageAdditionalServiceType;
	}

	public void setCottageAdditionalServiceType(CottageAdditionalServiceType cottageAdditionalServiceType) {
		this.cottageAdditionalServiceType = cottageAdditionalServiceType;
	}

}
