package Team76.InternetSoftwareArchitecture.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.service.CottageOwnerService;
import Team76.InternetSoftwareArchitecture.service.FishingInstructorService;
import Team76.InternetSoftwareArchitecture.service.ShipOwnerService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemAdministratorController {

	private CottageOwnerService cottageOwnerService;

	private ShipOwnerService shipOwnerService;

	private FishingInstructorService fishingInstructorService;

	@Autowired
	public SystemAdministratorController(CottageOwnerService cottageOwnerService, ShipOwnerService shipOwnerService,
			FishingInstructorService fishingInstructorService) {
		super();
		this.cottageOwnerService = cottageOwnerService;
		this.shipOwnerService = shipOwnerService;
		this.fishingInstructorService = fishingInstructorService;
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping("/registrationRequests")
	public ResponseEntity<?> registrationRequests() {
		ArrayList<User> usersForAccountApproval = new ArrayList<User>();
		ArrayList<User> cottageOwnersForAccountApproval = (ArrayList<User>) cottageOwnerService
				.findAllCottageOwnersByAccountApproval(AccountApproval.WAITING);
		ArrayList<User> shipOwnersForAccountApproval = (ArrayList<User>) shipOwnerService
				.findAllShipOwnersByAccountApproval(AccountApproval.WAITING);
		ArrayList<User> fishingInstructorsForAccountApproval = (ArrayList<User>) fishingInstructorService
				.findAllFishingInstructorsByAccountApproval(AccountApproval.WAITING);

		for (User user : cottageOwnersForAccountApproval) {
			usersForAccountApproval.add(user);
		}

		for (User user : shipOwnersForAccountApproval) {
			usersForAccountApproval.add(user);
		}

		for (User user : fishingInstructorsForAccountApproval) {
			usersForAccountApproval.add(user);
		}

		return new ResponseEntity<>(usersForAccountApproval, HttpStatus.OK);
	}
}
