package Team76.InternetSoftwareArchitecture.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotionId", unique = true, nullable = false)
	private Long promotionId;

	@Column(name = "caption", nullable = false)
	private String caption;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "startOfPromotion", nullable = false)
	private Date startOfPromotion;

	@Column(name = "endOfPromotion", nullable = false)
	private Date endOfPromotion;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = true)
	private User user;

	public Promotion() {

	}

	public Promotion(String caption, String description, Date startOfPromotion, Date endOfPromotion, User user) {
		super();
		this.caption = caption;
		this.description = description;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
		this.user = user;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartOfPromotion() {
		return startOfPromotion;
	}

	public void setStartOfPromotion(Date startOfPromotion) {
		this.startOfPromotion = startOfPromotion;
	}

	public Date getEndOfPromotion() {
		return endOfPromotion;
	}

	public void setEndOfPromotion(Date endOfPromotion) {
		this.endOfPromotion = endOfPromotion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
