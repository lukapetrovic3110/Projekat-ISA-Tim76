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

import Team76.InternetSoftwareArchitecture.dto.ShipNavigationEquipmentDTO;
import Team76.InternetSoftwareArchitecture.service.NavigationEquipmentService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/navigationEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
public class NavigationEquipmentController {

	private NavigationEquipmentService navigationEquipmentService;

	@Autowired
	public NavigationEquipmentController(NavigationEquipmentService navigationEquipmentService) {
		super();
		this.navigationEquipmentService = navigationEquipmentService;
	}
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@GetMapping
	public ResponseEntity<List<ShipNavigationEquipmentDTO>> get() {
		return new ResponseEntity<List<ShipNavigationEquipmentDTO>>(navigationEquipmentService.getAllShipNavigationEquipment(), HttpStatus.OK);
	}
}
