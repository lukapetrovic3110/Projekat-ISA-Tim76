package Team76.InternetSoftwareArchitecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.AdventureDTO;
import Team76.InternetSoftwareArchitecture.model.Adventure;
import Team76.InternetSoftwareArchitecture.service.AdventureService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/adventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {
	
	private AdventureService adventureService;
	
	@Autowired
	public AdventureController(AdventureService adventureService) {
		this.adventureService = adventureService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Adventure>> all() {
		return new ResponseEntity<List<Adventure>>(adventureService.all(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AdventureDTO>> findAllAdventureWithStatusActive() {
		return new ResponseEntity<List<AdventureDTO>>(adventureService.findAllAdventureWithStatusActive(), HttpStatus.OK);
	}
	
	@GetMapping("/findAdventure/{adventureId}")
	public ResponseEntity<AdventureDTO> getAdventureById(@PathVariable Long adventureId) {
		return new ResponseEntity<AdventureDTO>(adventureService.getAdventureById(adventureId), HttpStatus.OK);
	}
	
	
}
