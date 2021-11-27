package Team76.InternetSoftwareArchitecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ruleId", unique = true, nullable = false)
	private Long ruleId;
	
	@Column(name = "description", unique = true, nullable = false)
	private String description;

	public Rule() { }
	
	public Rule(String description) {
		super();
		this.description = description;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
