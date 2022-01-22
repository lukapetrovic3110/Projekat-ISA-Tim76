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

import Team76.InternetSoftwareArchitecture.dto.CottageFastReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationCalendarDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationInformationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageReservationReportDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.CreateReservationResponseDTO;
import Team76.InternetSoftwareArchitecture.dto.DeleteCottageReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.HistoryReservationCottageDTO;
import Team76.InternetSoftwareArchitecture.dto.ScheduleFastReservationDTO;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationStatus;
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
		return new ResponseEntity<List<CottageReservationInformationDTO>>(reservationCottageService.findAllReservationsForCottageOwner(id, null), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping("/cottageOwnerFinishedReservations/{id}")
	public ResponseEntity<List<CottageReservationInformationDTO>> findAllFinishedReservationsForCottageOwner(@PathVariable Long id) {
		return new ResponseEntity<List<CottageReservationInformationDTO>>(reservationCottageService.findAllReservationsForCottageOwner(id, ReservationStatus.FINISHED), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PostMapping("/report")
	public ResponseEntity<CottageReservationReportDTO> saveReport(@RequestBody CottageReservationReportDTO cottageReservationReportDTO) {
		return new ResponseEntity<CottageReservationReportDTO>(reservationCottageService.saveReport(cottageReservationReportDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_COTTAGE_OWNER', 'ROLE_CLIENT')")
	@GetMapping("/cottageFastReservations/{cottageId}")
	public ResponseEntity<List<CottageFastReservationDTO>> findAllFastReservationsForCottage(@PathVariable Long cottageId) {
		return new ResponseEntity<List<CottageFastReservationDTO>>(reservationCottageService.findAllFastReservationsForCottage(cottageId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PostMapping("/saveFastReservation/{id}")
	public ResponseEntity<CottageFastReservationDTO> saveFastReservation(@PathVariable Long id, @RequestBody CottageFastReservationDTO cottageFastReservationDTO) {
		return new ResponseEntity<CottageFastReservationDTO>(reservationCottageService.saveFastReservation(id, cottageFastReservationDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/scheduleFastReservation")
	public ResponseEntity<?> scheduleFastReservation(@RequestBody ScheduleFastReservationDTO scheduleFastReservationDTO) {
		try {
			return new ResponseEntity<Boolean>(reservationCottageService.scheduleFastReservation(scheduleFastReservationDTO.getFastReservationId()), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/createReservation")
	public ResponseEntity<CreateReservationResponseDTO> createReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
		try {
			return new ResponseEntity<CreateReservationResponseDTO>(reservationCottageService.createReservation(createReservationRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CreateReservationResponseDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PostMapping("/deleteFastReservation")
	public ResponseEntity<Boolean> deleteFastReservation(@RequestBody DeleteCottageReservationDTO deleteCottageReservationDTO) {
		try {
			return new ResponseEntity<Boolean>(reservationCottageService.deleteFastReservation(deleteCottageReservationDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping("/availabilityCalendar/{id}")
	public ResponseEntity<CottageReservationCalendarDTO> getAvailabilityCalendarInformation(@PathVariable Long id) {
		return new ResponseEntity<CottageReservationCalendarDTO>(reservationCottageService.getAvailabilityCalendarInformation(id), HttpStatus.OK);
	}
	
}
