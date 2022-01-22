package Team76.InternetSoftwareArchitecture.iservice;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ImageUploadDTO;
import Team76.InternetSoftwareArchitecture.dto.ImagesDTO;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Service
public interface IImageService {
	
	List<Image> upload(ImageUploadDTO imageUploadDTO, Long entityId, UserType userType) throws IOException;
	
	List<Image> edit(ImageUploadDTO imageUploadDTO, Long entityId, UserType userType) throws IOException;
	
	Image saveImage(Image image);
	
	ImagesDTO getImages(Long id, String entity);
	
}
