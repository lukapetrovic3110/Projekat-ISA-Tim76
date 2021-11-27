package Team76.InternetSoftwareArchitecture.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "priceListId", unique = true, nullable = false)
	private Long priceListId;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PriceTag> priceTags = new HashSet<PriceTag>();
	
	public PriceList() { }
	
	public PriceList(Set<PriceTag> priceTags) {
		super();
		this.priceTags = priceTags;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}

	public Set<PriceTag> getPriceTags() {
		return priceTags;
	}

	public void setPriceTags(Set<PriceTag> priceTags) {
		this.priceTags = priceTags;
	}
	
}
