package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IImageService;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.repository.IImageRepository;

@Service
public class ImageService implements IImageService {

	private IImageRepository imageRepository;
	
	@Autowired
	public ImageService(IImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}

	@Override
	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}

}
