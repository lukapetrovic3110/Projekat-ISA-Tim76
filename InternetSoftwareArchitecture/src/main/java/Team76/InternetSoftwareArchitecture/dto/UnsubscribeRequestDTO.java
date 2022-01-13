package Team76.InternetSoftwareArchitecture.dto;

public class UnsubscribeRequestDTO {
	
	private Long id;
	
	public UnsubscribeRequestDTO() {
	
	}

	public UnsubscribeRequestDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
