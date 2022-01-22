package Team76.InternetSoftwareArchitecture.controller;

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

import Team76.InternetSoftwareArchitecture.dto.CreateReservationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationResponseDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationShipDTO;
import Team76.InternetSoftwareArchitecture.dto.ScheduleFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipFastReservationDTO;
import Team76.InternetSoftwareArchitecture.service.ReservationShipService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/reservationShip", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationShipController {
	
	private ReservationShipService reservationShipService;
	
	@Autowired
	public ReservationShipController(ReservationShipService reservationShipService) {
		this.reservationShipService = reservationShipService;
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/clientHistory")
	public ResponseEntity<List<HistoryReservationShipDTO>> findAllHistoryReservationByClient() {
		return new ResponseEntity<List<HistoryReservationShipDTO>>(reservationShipService.findAllHistoryReservationByClient(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SHIP_OWNER', 'ROLE_CLIENT')")
	@GetMapping("/shipFastReservations/{shipId}")
	public ResponseEntity<List<ShipFastReservationDTO>> findAllFastReservationsForShip(@PathVariable Long shipId) {
		return new ResponseEntity<List<ShipFastReservationDTO>>(reservationShipService.findAllFastReservationsForShip(shipId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/scheduleFastReservation")
	public ResponseEntity<?> scheduleFastReservation(@RequestBody ScheduleFastReservationDTO scheduleFastReservationDTO) {
		try {
			return new ResponseEntity<Boolean>(reservationShipService.scheduleFastReservation(scheduleFastReservationDTO.getFastReservationId()), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/createReservation")
	public ResponseEntity<CreateReservationResponseDTO> createReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
		try {
			return new ResponseEntity<CreateReservationResponseDTO>(reservationShipService.createReservation(createReservationRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CreateReservationResponseDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
