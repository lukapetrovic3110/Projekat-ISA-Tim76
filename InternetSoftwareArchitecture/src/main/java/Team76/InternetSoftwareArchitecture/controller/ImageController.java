package Team76.InternetSoftwareArchitecture.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.ImageUploadDTO;
import Team76.InternetSoftwareArchitecture.dto.ImagesDTO;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.service.ImageService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
	
	private ImageService imageService;
	
	@Autowired
	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_COTTAGE_OWNER', 'ROLE_SHIP_OWNER', 'ROLE_FISHING_INSTRUCTOR')")
	@PostMapping("/upload/{id}/{userType}")
	public ResponseEntity<?> upload(@RequestBody ImageUploadDTO imageUploadDTO, @PathVariable Long id, @PathVariable UserType userType) throws IOException {
		return new ResponseEntity<>(imageService.upload(imageUploadDTO, id, userType), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_COTTAGE_OWNER', 'ROLE_SHIP_OWNER', 'ROLE_FISHING_INSTRUCTOR')")
	@PostMapping("/edit/{id}/{userType}")
	public ResponseEntity<?> edit(@RequestBody ImageUploadDTO imageUploadDTO, @PathVariable Long id, @PathVariable UserType userType) throws IOException {
		return new ResponseEntity<>(imageService.edit(imageUploadDTO, id, userType), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_COTTAGE_OWNER', 'ROLE_SHIP_OWNER', 'ROLE_FISHING_INSTRUCTOR', 'ROLE_CLIENT')")
	@GetMapping("/getImages/{entity}/{id}")
	public ResponseEntity<ImagesDTO> get(@PathVariable Long id, @PathVariable String entity) {
		return new ResponseEntity<ImagesDTO>(imageService.getImages(id, entity), HttpStatus.OK);
	}
}
