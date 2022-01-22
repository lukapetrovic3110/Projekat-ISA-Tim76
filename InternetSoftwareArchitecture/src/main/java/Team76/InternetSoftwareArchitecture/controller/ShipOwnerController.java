package Team76.InternetSoftwareArchitecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.service.ShipOwnerService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/shipOwner", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShipOwnerController {
	
	private ShipOwnerService shipOwnerService;

	@Autowired
	public ShipOwnerController(ShipOwnerService shipOwnerService) {
		super();
		this.shipOwnerService = shipOwnerService;
	}
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@GetMapping
	public ResponseEntity<ShipOwner> getCottageOwner() {
		return new ResponseEntity<ShipOwner>(shipOwnerService.findShipOwner(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@PostMapping(value = "/update")
	public ResponseEntity<ShipOwner> update(@RequestBody ShipOwner shipOwner) throws Exception {
		return new ResponseEntity<ShipOwner>(shipOwnerService.update(shipOwner), HttpStatus.OK);
	}
	
}
