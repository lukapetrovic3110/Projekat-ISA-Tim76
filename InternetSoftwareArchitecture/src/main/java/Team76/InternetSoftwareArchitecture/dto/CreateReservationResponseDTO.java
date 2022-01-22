package Team76.InternetSoftwareArchitecture.dto;

public class CreateReservationResponseDTO {
	
	private Boolean isCreated;
	private String answer;
	
	public CreateReservationResponseDTO() {
	
	}
	
	public CreateReservationResponseDTO(Boolean isCreated, String answer) {
		super();
		this.isCreated = isCreated;
		this.answer = answer;
	}

	public Boolean getIsCreated() {
		return isCreated;
	}

	public void setIsCreated(Boolean isCreated) {
		this.isCreated = isCreated;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
