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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.CottageReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
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
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/scheduledReservation")
	public ResponseEntity<List<ReservationCottage>> findAllScheduledReservationByClient() {
		return new ResponseEntity<List<ReservationCottage>>(reservationCottageService.findAllScheduledReservationByClient(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping("/cottageOwnerReservations/{id}")
	public ResponseEntity<List<CottageReservationInformationDTO>> findAllReservationsForCottageOwner(@PathVariable Long id) {
		return new ResponseEntity<List<CottageReservationInformationDTO>>(reservationCottageService.findAllReservationsForCottageOwner(id), HttpStatus.OK);
	}
}
