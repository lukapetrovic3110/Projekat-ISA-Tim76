package Team76.InternetSoftwareArchitecture.model;

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
import javax.persistence.ManyToOne;

@Entity
public class Revision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revisionId", unique = true, nullable = false)
	private Long revisionId;

	@Column(name = "rating", nullable = false)
	private Integer rating;

	@Column(name = "comment", nullable = false)
	private String comment;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId", referencedColumnName = "userId")
	private Client client;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = true)
	private User user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cottageId", referencedColumnName = "cottageId", nullable = true)
	private Cottage cottage;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "shipId", referencedColumnName = "shipId", nullable = true)
	private Ship ship;

	@Enumerated(EnumType.STRING)
	private RevisionStatus revisionStatus;

	public Revision() {

	}

	public Revision(Integer rating, String comment, Client client, User user, RevisionStatus revisionStatus,
			Cottage cottage, Ship ship) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.client = client;
		this.user = user;
		this.revisionStatus = revisionStatus;
		this.cottage = cottage;
		this.ship = ship;
	}

	public Long getRevisionId() {
		return revisionId;
	}

	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RevisionStatus getRevisionStatus() {
		return revisionStatus;
	}

	public void setRevisionStatus(RevisionStatus revisionStatus) {
		this.revisionStatus = revisionStatus;
	}

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

}
