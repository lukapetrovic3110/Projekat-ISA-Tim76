package Team76.InternetSoftwareArchitecture.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.ImageDTO;
import Team76.InternetSoftwareArchitecture.dto.ImagesDTO;
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

	@Override
	public ImagesDTO getImages(Long id, String entity) {
		ImagesDTO imagesDTO = new ImagesDTO(new ArrayList<ImageDTO>());
		boolean defaultImage = true;
		if (entity.equals("cottage")) {
			List<Long> imagesId = imageRepository.getAllImageIdForCottage(id);
			List<Image> images = imageRepository.findAll();
			for (Image image : images) {
				if (imagesId.contains(image.getImageId())) {
					try {
						byte[] imageFileContent = FileUtils.readFileToByteArray(new File("../internetsoftwarearchitecturefrontend/src/assets/images/" + image.getName() + ".jpg"));
						String imageEncodedString = Base64.getEncoder().encodeToString(imageFileContent);
						if (defaultImage) {
							imagesDTO.getImages().add(new ImageDTO(1, 1, image.getName() + ".jpg", "data:image/jpeg;base64," + imageEncodedString));
							defaultImage = false;
						} else {
							imagesDTO.getImages().add(new ImageDTO(0, 0, image.getName() + ".jpg", "data:image/jpeg;base64," + imageEncodedString));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (entity.equals("ship")) {
			// Get images for ship
		} else {
			// Get images for fishing adventure
		}
		
		return imagesDTO;
	}

}
