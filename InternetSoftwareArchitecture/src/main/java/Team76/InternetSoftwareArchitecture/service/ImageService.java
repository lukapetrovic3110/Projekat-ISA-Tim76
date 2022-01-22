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
import Team76.InternetSoftwareArchitecture.dto.ImageUploadDTO;
import Team76.InternetSoftwareArchitecture.dto.ImagesDTO;
import Team76.InternetSoftwareArchitecture.iservice.IImageService;
import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.repository.IImageRepository;

@Service
public class ImageService implements IImageService {

	private IImageRepository imageRepository;
	
	private CottageService cottageService;
	
	@Autowired
	public ImageService(IImageRepository imageRepository, CottageService cottageService) {
		super();
		this.imageRepository = imageRepository;
		this.cottageService = cottageService;
	}

	@Override
	public List<Image> upload(ImageUploadDTO imageUploadDTO, Long entityId, UserType userType) throws IOException {
		List<Image> cottageImages = new ArrayList<Image>();
		List<String> images = imageUploadDTO.getImagesInformation();
		for (String image : images) {
			String[] imageInfo = image.split(",");
			
			String newImageName = System.currentTimeMillis() + imageInfo[0];
			File outputFile = new File("../internetsoftwarearchitecturefrontend/src/assets/images/" + newImageName);
			byte[] decodedBytes = Base64.getDecoder().decode(imageInfo[2]);

			FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
			Image imagedb = saveImage(new Image(newImageName.substring(0, newImageName.length() - 4)));
			cottageImages.add(imagedb);
			
			if (userType == UserType.COTTAGE_OWNER) {
				cottageService.saveImageForCottage(entityId, imagedb.getImageId());
			} else if (userType == UserType.SHIP_OWNER) {
				// Add image for ship
			} else {
				// Add image for fishing adventure
			}
		}
		
		return cottageImages;
	}
	
	@Override
	public List<Image> edit(ImageUploadDTO imageUploadDTO, Long entityId, UserType userType) throws IOException {
		if (userType == UserType.COTTAGE_OWNER) {
			cottageService.deleteImagesForCottage(entityId);
		} else if (userType == UserType.SHIP_OWNER) {
			// Delete images for ship
		} else {
			// Delete images for fishing adventure
		}
		
		List<Image> cottageImages = new ArrayList<Image>();
		List<String> images = imageUploadDTO.getImagesInformation();
		for (String image : images) {
			String[] imageInfo = image.split(",");
			
			String newImageName = System.currentTimeMillis() + imageInfo[0];
			File outputFile = new File("../internetsoftwarearchitecturefrontend/src/assets/images/" + newImageName);
			byte[] decodedBytes = Base64.getDecoder().decode(imageInfo[2]);

			FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
			Image imagedb = saveImage(new Image(newImageName.substring(0, newImageName.length() - 4)));
			cottageImages.add(imagedb);
			
			if (userType == UserType.COTTAGE_OWNER) {
				cottageService.saveImageForCottage(entityId, imagedb.getImageId());
			} else if (userType == UserType.SHIP_OWNER) {
				// Add image for ship
			} else {
				// Add image for fishing adventure
			}
		}
		
		return cottageImages;
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
