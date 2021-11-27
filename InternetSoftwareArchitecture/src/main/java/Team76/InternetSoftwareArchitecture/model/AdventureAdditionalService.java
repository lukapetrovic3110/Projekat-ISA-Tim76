package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdventureAdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adventureAdditionalServiceId", unique = true, nullable = false)
	private Long adventureAdditionalServiceId;

	@Enumerated(EnumType.STRING)
	private AdventureAdditionalServiceType adventureAdditionalServiceType;

	public AdventureAdditionalService() {

	}

	public AdventureAdditionalService(AdventureAdditionalServiceType adventureAdditionalServiceType) {
		super();
		this.adventureAdditionalServiceType = adventureAdditionalServiceType;
	}

	public Long getAdventureAdditionalServiceId() {
		return adventureAdditionalServiceId;
	}

	public void setAdventureAdditionalServiceId(Long adventureAdditionalServiceId) {
		this.adventureAdditionalServiceId = adventureAdditionalServiceId;
	}

	public AdventureAdditionalServiceType getAdventureAdditionalServiceType() {
		return adventureAdditionalServiceType;
	}

	public void setAdventureAdditionalServiceType(AdventureAdditionalServiceType adventureAdditionalServiceType) {
		this.adventureAdditionalServiceType = adventureAdditionalServiceType;
	}

}
