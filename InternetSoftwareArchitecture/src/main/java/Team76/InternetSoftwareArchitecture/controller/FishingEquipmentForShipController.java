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

import Team76.InternetSoftwareArchitecture.dto.FishingEquipmentForShipDTO;
import Team76.InternetSoftwareArchitecture.service.FishingEquipmentForShipService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/fishingEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishingEquipmentForShipController {

	private FishingEquipmentForShipService fishingEquipmentForShipService;

	@Autowired
	public FishingEquipmentForShipController(FishingEquipmentForShipService fishingEquipmentForShipService) {
		super();
		this.fishingEquipmentForShipService = fishingEquipmentForShipService;
	}
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@GetMapping
	public ResponseEntity<List<FishingEquipmentForShipDTO>> get() {
		return new ResponseEntity<List<FishingEquipmentForShipDTO>>(fishingEquipmentForShipService.getAllFishingEquipmentForShip(), HttpStatus.OK);
	}
}
