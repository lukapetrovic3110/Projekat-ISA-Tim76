package Team76.InternetSoftwareArchitecture.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Team76.InternetSoftwareArchitecture.model.Image;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.service.CottageService;
import Team76.InternetSoftwareArchitecture.service.ImageService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
	
	private ImageService imageService;
	
	private CottageService cottageService;
	
	@Autowired
	public ImageController(ImageService imageService, CottageService cottageService) {
		super();
		this.imageService = imageService;
		this.cottageService = cottageService;
	}

	@PostMapping("/upload/{id}/{userType}")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile[] multipartFile, @PathVariable Long id, @PathVariable UserType userType) throws IOException {
		for (int i = 0; i < multipartFile.length; i++) {
			byte[] fileContent = multipartFile[i].getBytes();
			String encodedString = Base64.getEncoder().encodeToString(fileContent);		
			
			String newImageName = System.currentTimeMillis() + multipartFile[i].getOriginalFilename(); 
			File outputFile = new File("./images/" + newImageName);
			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
			FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
			
			Image imagedb = imageService.saveImage(new Image(newImageName));
			
			if (userType == UserType.COTTAGE_OWNER) {
				cottageService.saveImageForCottage(id, imagedb.getImageId());
			} else if (userType == UserType.SHIP_OWNER) {
				// Add image for ship
			} else {
				// Add image for fishing adventure
			}
			
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
