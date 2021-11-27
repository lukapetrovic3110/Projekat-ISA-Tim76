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
import javax.persistence.OneToOne;

@Entity
public class WorkingTimeAdventure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workingTimeAdventureId", unique = true, nullable = false)
	private Long workingTimeAdventureId;

	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "adventureId", referencedColumnName = "adventureId")
	private Adventure Adventure;
	
	public WorkingTimeAdventure() { }

	public WorkingTimeAdventure(Date startDate, Date endDate,
			Team76.InternetSoftwareArchitecture.model.Adventure adventure) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		Adventure = adventure;
	}

	public Long getWorkingTimeAdventureId() {
		return workingTimeAdventureId;
	}

	public void setWorkingTimeAdventureId(Long workingTimeAdventureId) {
		this.workingTimeAdventureId = workingTimeAdventureId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Adventure getAdventure() {
		return Adventure;
	}

	public void setAdventure(Adventure adventure) {
		Adventure = adventure;
	}
		
}
