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

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationAdventureDTO;
import Team76.InternetSoftwareArchitecture.service.ReservationAdventureService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/reservationAdventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationAdventureController {
	
	private ReservationAdventureService reservationAdventureService;
	
	@Autowired
	public ReservationAdventureController(ReservationAdventureService reservationAdventureService) {
		this.reservationAdventureService = reservationAdventureService;
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/clientHistory")
	public ResponseEntity<List<HistoryReservationAdventureDTO>> findAllHistoryReservationByClient() {
		return new ResponseEntity<List<HistoryReservationAdventureDTO>>(reservationAdventureService.findAllHistoryReservationByClient(), HttpStatus.OK);
	}
	
	
}
