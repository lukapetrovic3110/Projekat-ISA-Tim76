package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ImagesDTO;
import Team76.InternetSoftwareArchitecture.model.Image;

@Service
public interface IImageService {
	
	Image saveImage(Image image);
	
	ImagesDTO getImages(Long id, String entity);
	
}
