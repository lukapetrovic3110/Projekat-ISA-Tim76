package Team76.InternetSoftwareArchitecture.dto;

import java.util.List;

public class ImageUploadDTO {
	
	private List<String> imagesInformation;
	
	public ImageUploadDTO() {
		
	}

	public ImageUploadDTO(List<String> imagesInformation) {
		super();
		this.imagesInformation = imagesInformation;
	}

	public List<String> getImagesInformation() {
		return imagesInformation;
	}

	public void setImagesInformation(List<String> imagesInformation) {
		this.imagesInformation = imagesInformation;
	}
	
}
