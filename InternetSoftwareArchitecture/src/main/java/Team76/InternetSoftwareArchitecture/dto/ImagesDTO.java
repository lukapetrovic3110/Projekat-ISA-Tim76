package Team76.InternetSoftwareArchitecture.dto;

import java.util.List;

public class ImagesDTO {
	
	private List<ImageDTO> images;
	
	public ImagesDTO() {
		
	}

	public ImagesDTO(List<ImageDTO> images) {
		super();
		this.images = images;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	
}
