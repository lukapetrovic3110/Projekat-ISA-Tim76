package Team76.InternetSoftwareArchitecture.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Client extends User {

	private static final long serialVersionUID = -4175962719206765628L;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FishingInstructor> fishingInstructorSubscriptions = new HashSet<FishingInstructor>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Cottage> cottageSubscriptions = new HashSet<Cottage>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Ship> shipSubscriptions = new HashSet<Ship>();
	
	public Client() {
		
	}

	public Client(Set<FishingInstructor> fishingInstructorSubscriptions, Set<Cottage> cottageSubscriptions,
			Set<Ship> shipSubscriptions) {
		super();
		this.fishingInstructorSubscriptions = fishingInstructorSubscriptions;
		this.cottageSubscriptions = cottageSubscriptions;
		this.shipSubscriptions = shipSubscriptions;
	}

	public Set<FishingInstructor> getFishingInstructorSubscriptions() {
		return fishingInstructorSubscriptions;
	}

	public void setFishingInstructorSubscriptions(Set<FishingInstructor> fishingInstructorSubscriptions) {
		this.fishingInstructorSubscriptions = fishingInstructorSubscriptions;
	}

	public Set<Cottage> getCottageSubscriptions() {
		return cottageSubscriptions;
	}

	public void setCottageSubscriptions(Set<Cottage> cottageSubscriptions) {
		this.cottageSubscriptions = cottageSubscriptions;
	}

	public Set<Ship> getShipSubscriptions() {
		return shipSubscriptions;
	}

	public void setShipSubscriptions(Set<Ship> shipSubscriptions) {
		this.shipSubscriptions = shipSubscriptions;
	}
	
}
