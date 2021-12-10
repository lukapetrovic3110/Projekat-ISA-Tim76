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

import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.service.ReservationCottageService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/reservationCottage", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationCottageController {
	
	private ReservationCottageService reservationCottageService;

	@Autowired
	public ReservationCottageController(ReservationCottageService reservationCottageService) {
		this.reservationCottageService = reservationCottageService;
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/clientHistory")
	public ResponseEntity<List<HistoryReservationCottageDTO>> findAllHistoryReservationByClient() {
		return new ResponseEntity<List<HistoryReservationCottageDTO>>(reservationCottageService.findAllHistoryReservationByClient(), HttpStatus.OK);
	}
	
}
