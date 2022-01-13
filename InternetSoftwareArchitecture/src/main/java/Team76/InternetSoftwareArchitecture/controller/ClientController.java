package Team76.InternetSoftwareArchitecture.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.CancelReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.CottageSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.dto.FishingInstructorSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.dto.ScheduledReservationDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.dto.UnsubscribeRequestDTO;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ReservationAdventure;
import Team76.InternetSoftwareArchitecture.model.ReservationCottage;
import Team76.InternetSoftwareArchitecture.model.ReservationShip;
import Team76.InternetSoftwareArchitecture.model.ReservationType;
import Team76.InternetSoftwareArchitecture.service.ClientService;
import Team76.InternetSoftwareArchitecture.service.ReservationAdventureService;
import Team76.InternetSoftwareArchitecture.service.ReservationCottageService;
import Team76.InternetSoftwareArchitecture.service.ReservationShipService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	private ClientService clientService;
	private ReservationCottageService reservationCottageService;
	private ReservationShipService reservationShipService;
	private ReservationAdventureService reservationAdventureService;
	
	@Autowired
	public ClientController(ClientService clientService, ReservationCottageService reservationCottageService,
			ReservationShipService reservationShipService, ReservationAdventureService reservationAdventureService) {
		this.clientService = clientService;
		this.reservationCottageService = reservationCottageService;
		this.reservationShipService = reservationShipService;
		this.reservationAdventureService = reservationAdventureService;
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping
	public ResponseEntity<Client> getClient() {
		return new ResponseEntity<Client>(clientService.findClient(), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/shipSubscriptions")
	public ResponseEntity<List<ShipSubscriptionDTO>> getShipSubscriptions() {
		return new ResponseEntity<List<ShipSubscriptionDTO>>(clientService.getShipSubscriptions(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PutMapping(value = "/unsubscribeShip")
	public ResponseEntity<Boolean> unsubscribeShip(@RequestBody UnsubscribeRequestDTO unsubscribeRequestDTO) {
		return new ResponseEntity<Boolean>(clientService.unsubscribeShip(unsubscribeRequestDTO.getId()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/cottageSubscriptions")
	public ResponseEntity<List<CottageSubscriptionDTO>> getCottageSubscriptions() {
		return new ResponseEntity<List<CottageSubscriptionDTO>>(clientService.getCottageSubscriptions(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PutMapping(value = "/unsubscribeCottage")
	public ResponseEntity<Boolean> unsubscribeCottage(@RequestBody UnsubscribeRequestDTO unsubscribeRequestDTO) {
		return new ResponseEntity<Boolean>(clientService.unsubscribeCottage(unsubscribeRequestDTO.getId()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/fishingInstructorSubscriptions")
	public ResponseEntity<List<FishingInstructorSubscriptionDTO>> getFishingInstructorSubscriptions() {
		return new ResponseEntity<List<FishingInstructorSubscriptionDTO>>(clientService.getFishingInstructorSubscriptions(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PutMapping(value = "/unsubscribeFishingInstructor")
	public ResponseEntity<Boolean> unsubscribeFishingInstructor(@RequestBody UnsubscribeRequestDTO unsubscribeRequestDTO) {
		return new ResponseEntity<Boolean>(clientService.unsubscribeFishingInstructor(unsubscribeRequestDTO.getId()), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/update")
	public ResponseEntity<Client> update(@RequestBody Client client) throws Exception {
		return new ResponseEntity<Client>(clientService.update(client), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping(value = "/scheduledReservation")
	public ResponseEntity<List<ScheduledReservationDTO>> allScheduledReservation() {
		List<ScheduledReservationDTO> scheduledReservationDTOs = new ArrayList<ScheduledReservationDTO>();
		for (ReservationShip reservationShip : reservationShipService.findAllScheduledReservationByClient()) {
			
			StringBuilder duration = new StringBuilder();
			duration.append(reservationShip.getDuration());
			if (reservationShip.getDuration() > 1) 
				 duration.append(" hours");
			else 
				duration.append(" hour");
			
			scheduledReservationDTOs.add(new ScheduledReservationDTO(reservationShip.getReservationShipId(), ReservationType.SHIP, reservationShip.getDateAndTime().toString(), duration.toString(), reservationShip.getPrice(), reservationShip.getMaxNumberOfPersons(), reservationShip.getShip().getName() + " - ship(boat)"));
		}
		
		for (ReservationAdventure reservationAdventure : reservationAdventureService.findAllScheduledReservationByClient()) {
			
			StringBuilder duration = new StringBuilder();
			duration.append(reservationAdventure.getDuration());
			if (reservationAdventure.getDuration() > 1) 
				 duration.append(" hours");
			else 
				duration.append(" hour");
			
			scheduledReservationDTOs.add(new ScheduledReservationDTO(reservationAdventure.getReservationAdventureId(), ReservationType.ADVENTURE, reservationAdventure.getDateAndTime().toString(), duration.toString(), reservationAdventure.getPrice(), reservationAdventure.getMaxNumberOfPersons(), reservationAdventure.getAdventure().getName() + " - adventure"));
		}
		
		for (ReservationCottage reservationCottage : reservationCottageService.findAllScheduledReservationByClient()) {
			
			StringBuilder duration = new StringBuilder();
			duration.append(reservationCottage.getDuration());
			if (reservationCottage.getDuration() > 1) 
				 duration.append(" days");
			else 
				duration.append(" day");
			
			scheduledReservationDTOs.add(new ScheduledReservationDTO(reservationCottage.getReservationCottageId(), ReservationType.COTTAGE, reservationCottage.getDateAndTime().toString(), duration.toString(), reservationCottage.getPrice(), reservationCottage.getMaxNumberOfPersons(), reservationCottage.getCottage().getName() + " - cottage"));
		}
	
		return new ResponseEntity<List<ScheduledReservationDTO>>(scheduledReservationDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PutMapping(value = "/cancelReservation")
	public ResponseEntity<Boolean> cancelReservation(@RequestBody CancelReservationDTO cancelReservationDTO) {
		Boolean isCancelReseravation = false;
		
		if (cancelReservationDTO.getReservationType() == ReservationType.ADVENTURE) {
			isCancelReseravation = reservationAdventureService.cancelReservation(cancelReservationDTO.getReservationId());
		}
		
		if (cancelReservationDTO.getReservationType() == ReservationType.SHIP) {
			isCancelReseravation = reservationShipService.cancelReservation(cancelReservationDTO.getReservationId());
		}
		
		if (cancelReservationDTO.getReservationType() == ReservationType.COTTAGE) {
			isCancelReseravation = reservationCottageService.cancelReservation(cancelReservationDTO.getReservationId());
		}
		
		return new ResponseEntity<Boolean>(isCancelReseravation, HttpStatus.OK);
	}

}
