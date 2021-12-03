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
	private Set<User> subscriptions = new HashSet<User>();

	public Client() {

	}

	public Client(Set<User> subscriptions) {
		super();
		this.subscriptions = subscriptions;
	}

	public Set<User> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<User> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
