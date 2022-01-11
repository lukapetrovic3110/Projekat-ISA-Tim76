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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.AcceptRegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.DeclineRegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.WaitingRegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.service.CottageOwnerService;
import Team76.InternetSoftwareArchitecture.service.FishingInstructorService;
import Team76.InternetSoftwareArchitecture.service.ShipOwnerService;
import Team76.InternetSoftwareArchitecture.service.SystemAdministratorService;
import Team76.InternetSoftwareArchitecture.service.UserService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemAdministratorController {

	private SystemAdministratorService systemAdministratorService;
	
	private CottageOwnerService cottageOwnerService;

	private ShipOwnerService shipOwnerService;

	private FishingInstructorService fishingInstructorService;
	
	private UserService userService;

	@Autowired
	public SystemAdministratorController(SystemAdministratorService systemAdministratorService, CottageOwnerService cottageOwnerService, ShipOwnerService shipOwnerService,
			FishingInstructorService fishingInstructorService, UserService userService) {
		super();
		this.systemAdministratorService = systemAdministratorService;
		this.cottageOwnerService = cottageOwnerService;
		this.shipOwnerService = shipOwnerService;
		this.fishingInstructorService = fishingInstructorService;
		this.userService = userService;
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping
	public ResponseEntity<SystemAdministrator> getSystemAdministratior() {
		return new ResponseEntity<SystemAdministrator>(systemAdministratorService.findSystemAdministrator(), HttpStatus.OK);
	}
	

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping("/registrationRequests")
	public ResponseEntity<List<WaitingRegistrationRequestDTO>> findRegistrationRequests() {
		List<WaitingRegistrationRequestDTO> waitingRegistrationRequestDTOs = new ArrayList<WaitingRegistrationRequestDTO>();
		List<CottageOwner> cottageOwnersForAccountApproval = cottageOwnerService
				.findAllCottageOwnersByAccountApproval(AccountApproval.WAITING, UserType.COTTAGE_OWNER);
		List<ShipOwner> shipOwnersForAccountApproval = shipOwnerService
				.findAllShipOwnersByAccountApproval(AccountApproval.WAITING, UserType.SHIP_OWNER);
		List<FishingInstructor> fishingInstructorsForAccountApproval = fishingInstructorService
				.findAllFishingInstructorsByAccountApproval(AccountApproval.WAITING, UserType.FISHING_INSTRUCTOR);

		for (CottageOwner cottageOwner : cottageOwnersForAccountApproval) {
			waitingRegistrationRequestDTOs.add(new WaitingRegistrationRequestDTO(cottageOwner.getFirstName(), cottageOwner.getLastName(), cottageOwner.getEmail(), cottageOwner.getPhoneNumber(), AccountApproval.WAITING, cottageOwner.getExplanation()));
		}

		for (ShipOwner shipOwner : shipOwnersForAccountApproval) {
			waitingRegistrationRequestDTOs.add(new WaitingRegistrationRequestDTO(shipOwner.getFirstName(), shipOwner.getLastName(), shipOwner.getEmail(), shipOwner.getPhoneNumber(), AccountApproval.WAITING, shipOwner.getExplanation()));
		}

		for (FishingInstructor fishingInstructor : fishingInstructorsForAccountApproval) {
			waitingRegistrationRequestDTOs.add(new WaitingRegistrationRequestDTO(fishingInstructor.getFirstName(), fishingInstructor.getLastName(), fishingInstructor.getEmail(), fishingInstructor.getPhoneNumber(), AccountApproval.WAITING, fishingInstructor.getExplanation()));
		}
		
		return new ResponseEntity<>(waitingRegistrationRequestDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/acceptRegistrationRequest")
	public ResponseEntity<User> acceptRegistrationRequest(@RequestBody AcceptRegistrationRequestDTO acceptRegistrationRequestDTO) {
		try {
			return new ResponseEntity<User>(userService.acceptRegistrationRequest(acceptRegistrationRequestDTO),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/declineRegistrationRequest")
	public ResponseEntity<User> declineRegistarationRequest(@RequestBody DeclineRegistrationRequestDTO declineRegistrationRequestDTO) {
		try {
			return new ResponseEntity<User>(userService.declineRegistarationRequest(declineRegistrationRequestDTO),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

}
