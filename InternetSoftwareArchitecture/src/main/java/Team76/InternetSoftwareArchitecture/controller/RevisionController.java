package Team76.InternetSoftwareArchitecture.controller;

import java.util.List;
import java.util.regex.Pattern;

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

import Team76.InternetSoftwareArchitecture.dto.ObjectRevisionDTO;
import Team76.InternetSoftwareArchitecture.dto.RevisionRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.UserRevisionDTO;
import Team76.InternetSoftwareArchitecture.model.Revision;
import Team76.InternetSoftwareArchitecture.service.RevisionService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/revision", produces = MediaType.APPLICATION_JSON_VALUE)
public class RevisionController {
	
	private RevisionService revisionService;
	
	@Autowired
	public RevisionController(RevisionService revisionService) {
		this.revisionService = revisionService;
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/cottage")
	public ResponseEntity<List<ObjectRevisionDTO>> getCottageRevisions() {
		return new ResponseEntity<List<ObjectRevisionDTO>>(revisionService.getCottageRevisions(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/ship")
	public ResponseEntity<List<ObjectRevisionDTO>> getShipRevisions() {
		return new ResponseEntity<List<ObjectRevisionDTO>>(revisionService.getShipRevisions(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/adventure")
	public ResponseEntity<List<ObjectRevisionDTO>> getAdventureRevisions() {
		return new ResponseEntity<List<ObjectRevisionDTO>>(revisionService.getAdventureRevisions(), HttpStatus.OK);
	}	
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/user")
	public ResponseEntity<List<UserRevisionDTO>> getUserRevisions() {
		return new ResponseEntity<List<UserRevisionDTO>>(revisionService.getUserRevisions(), HttpStatus.OK);
	}	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeRevision")
	public ResponseEntity<?> writeRevision(@RequestBody RevisionRequestDTO revisionRequestDTO) {
		try {
			if (revisionRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(revisionRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(revisionRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else if(!(revisionRequestDTO.getRating() >= 1 && revisionRequestDTO.getRating() <= 5))
				throw new Exception("The rating must be in the range of 1 to 5!");
			else
				return new ResponseEntity<Revision>(revisionService.writeRevision(revisionRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
