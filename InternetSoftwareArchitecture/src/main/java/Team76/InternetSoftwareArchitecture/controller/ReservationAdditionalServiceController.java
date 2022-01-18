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

import Team76.InternetSoftwareArchitecture.dto.CottageAdditionalServiceDTO;
import Team76.InternetSoftwareArchitecture.service.CottageAdditionalServiceService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/reservationAdditionalService", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationAdditionalServiceController {
	
	private CottageAdditionalServiceService cottageAdditionalServiceService;

	@Autowired
	public ReservationAdditionalServiceController(CottageAdditionalServiceService cottageAdditionalServiceService) {
		super();
		this.cottageAdditionalServiceService = cottageAdditionalServiceService;
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping
	public ResponseEntity<List<CottageAdditionalServiceDTO>> get() {
		return new ResponseEntity<List<CottageAdditionalServiceDTO>>(cottageAdditionalServiceService.getAllCottageAdditionalServices(), HttpStatus.OK);
	}
}
