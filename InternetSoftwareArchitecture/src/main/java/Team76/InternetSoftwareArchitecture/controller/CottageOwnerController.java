package Team76.InternetSoftwareArchitecture.controller;

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

import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.service.CottageOwnerService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/cottageOwner", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageOwnerController {
	
	private CottageOwnerService cottageOwnerService;
	
	@Autowired
	public CottageOwnerController(CottageOwnerService cottageOwnerService) {
		super();
		this.cottageOwnerService = cottageOwnerService;
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@GetMapping
	public ResponseEntity<CottageOwner> getCottageOwner() {
		return new ResponseEntity<CottageOwner>(cottageOwnerService.findCottageOwner(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COTTAGE_OWNER')")
	@PostMapping(value = "/update")
	public ResponseEntity<CottageOwner> update(@RequestBody CottageOwner cottageOwner) throws Exception {
		return new ResponseEntity<CottageOwner>(cottageOwnerService.update(cottageOwner), HttpStatus.OK);
	}
	
}
