package Team76.InternetSoftwareArchitecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.ShipAdditionalServiceDTO;
import Team76.InternetSoftwareArchitecture.service.ShipAdditionalServiceService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/shipAdditionalService", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShipAdditionalServiceController {
	
	private ShipAdditionalServiceService shipAdditionalServiceService;

	@Autowired
	public ShipAdditionalServiceController(ShipAdditionalServiceService shipAdditionalServiceService) {
		super();
		this.shipAdditionalServiceService = shipAdditionalServiceService;
	}
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@GetMapping
	public ResponseEntity<List<ShipAdditionalServiceDTO>> get() {
		return new ResponseEntity<List<ShipAdditionalServiceDTO>>(shipAdditionalServiceService.getAllShipAdditionalServices(), HttpStatus.OK);
	}
}
