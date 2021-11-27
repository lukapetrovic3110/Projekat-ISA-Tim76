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
public class WorkingTimeCottage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workingTimeCottageId", unique = true, nullable = false)
	private Long workingTimeCottageId;

	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cottageId", referencedColumnName = "cottageId")
	private Cottage cottage;

	public WorkingTimeCottage() { }

	public WorkingTimeCottage(Date startDate, Date endDate, Cottage cottage) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.cottage = cottage;
	}

	public Long getWorkingTimeCottageId() {
		return workingTimeCottageId;
	}

	public void setWorkingTimeCottageId(Long workingTimeCottageId) {
		this.workingTimeCottageId = workingTimeCottageId;
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

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}
	
}
