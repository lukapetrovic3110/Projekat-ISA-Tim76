package Team76.InternetSoftwareArchitecture.dto;

public class ImageDTO {
	
	private Integer defaultImage;
	private Integer highlight;
	private String name;
	private String path;
	
	public ImageDTO() {
		
	}

	public ImageDTO(Integer defaultImage, Integer highlight, String name, String path) {
		super();
		this.defaultImage = defaultImage;
		this.highlight = highlight;
		this.name = name;
		this.path = path;
	}

	public Integer getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(Integer defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Integer getHighlight() {
		return highlight;
	}

	public void setHighlight(Integer highlight) {
		this.highlight = highlight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
