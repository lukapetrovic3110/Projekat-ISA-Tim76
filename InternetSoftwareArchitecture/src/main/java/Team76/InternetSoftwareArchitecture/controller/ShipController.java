package Team76.InternetSoftwareArchitecture.controller;

import java.util.Date;
import java.util.List;

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

import Team76.InternetSoftwareArchitecture.dto.AddShipDTO;
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
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@PostMapping("/add")
	public ResponseEntity<Ship> add(@RequestBody AddShipDTO addShipDTO) {
		return new ResponseEntity<Ship>(shipService.saveShip(addShipDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Ship>> all() {
		return new ResponseEntity<List<Ship>>(shipService.all(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SHIP_OWNER')")
	@GetMapping("/getAll/{id}")
	public ResponseEntity<List<ShipDTO>> getAll(@PathVariable Long id) {
		return new ResponseEntity<List<ShipDTO>>(shipService.getAllShipsForShipOwner(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ShipDTO>> findAllShipWithStatusActive() {
		return new ResponseEntity<List<ShipDTO>>(shipService.findAllShipWithStatusActive(), HttpStatus.OK);
	}
	
	@GetMapping("/findShip/{shipId}")
	public ResponseEntity<ShipDTO> getShipById(@PathVariable Long shipId) {
		return new ResponseEntity<ShipDTO>(shipService.getShipById(shipId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/findAvailableShipsForSelectedDateIntervalAndNumberOfGuests/{startDateAndTime}/{duration}/{numberOfGuests}")
	public ResponseEntity<List<ShipDTO>> findAvailableShipsForSelectedDateIntervalAndNumberOfGuests(@PathVariable Date startDateAndTime,@PathVariable Integer duration,@PathVariable Integer numberOfGuests) {
		return new ResponseEntity<List<ShipDTO>>(shipService.findAvailableShipsForSelectedDateIntervalAndNumberOfGuests(startDateAndTime, duration, numberOfGuests), HttpStatus.OK);
	}


}
