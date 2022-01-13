package Team76.InternetSoftwareArchitecture.dto;

public class SubscribeRequestDTO {
	
	Long id;
	
	public SubscribeRequestDTO() {
	
	}

	public SubscribeRequestDTO(Long id) {
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
