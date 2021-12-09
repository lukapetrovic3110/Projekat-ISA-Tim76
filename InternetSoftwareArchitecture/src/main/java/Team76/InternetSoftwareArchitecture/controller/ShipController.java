package Team76.InternetSoftwareArchitecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.ShipDTO;
import Team76.InternetSoftwareArchitecture.model.Ship;
import Team76.InternetSoftwareArchitecture.service.ShipService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/ship", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShipController {
	
	private ShipService shipService;
	
	@Autowired
	public ShipController(ShipService shipService) {
		this.shipService = shipService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Ship>> all() {
		return new ResponseEntity<List<Ship>>(shipService.all(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ShipDTO>> findAll() {
		return new ResponseEntity<List<ShipDTO>>(shipService.findAll(), HttpStatus.OK);
	}

}
