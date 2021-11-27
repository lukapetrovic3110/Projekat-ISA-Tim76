package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NavigationEquipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "navigationEquipmentId", unique = true, nullable = false)
	private Long navigationEquipmentId;
	
	@Enumerated(EnumType.STRING)
	private NavigationEquipmentType navigationEquipmentType;

	public NavigationEquipment() { }
	
	public NavigationEquipment(NavigationEquipmentType navigationEquipmentType) {
		super();
		this.navigationEquipmentType = navigationEquipmentType;
	}

	public Long getNavigationEquipmentId() {
		return navigationEquipmentId;
	}

	public void setNavigationEquipmentId(Long navigationEquipmentId) {
		this.navigationEquipmentId = navigationEquipmentId;
	}

	public NavigationEquipmentType getNavigationEquipmentType() {
		return navigationEquipmentType;
	}

	public void setNavigationEquipmentType(NavigationEquipmentType navigationEquipmentType) {
		this.navigationEquipmentType = navigationEquipmentType;
	}
	
}
