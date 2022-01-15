package Team76.InternetSoftwareArchitecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.model.Complaint;
import Team76.InternetSoftwareArchitecture.service.ComplaintService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {
	
	private ComplaintService complaintService;
	
	@Autowired
	public ComplaintController(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeCottageComplaint")
	public ResponseEntity<Complaint> writeCottageComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		return new ResponseEntity<Complaint>(complaintService.writeCottageComplaint(complaintRequestDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeCottageOwnerComplaint")
	public ResponseEntity<Complaint> writeCottageOwnerComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		return new ResponseEntity<Complaint>(complaintService.writeCottageOwnerComplaint(complaintRequestDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeShipComplaint")
	public ResponseEntity<Complaint> writeShipComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		return new ResponseEntity<Complaint>(complaintService.writeShipComplaint(complaintRequestDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeShipOwnerComplaint")
	public ResponseEntity<Complaint> writeShipOwnerComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		return new ResponseEntity<Complaint>(complaintService.writeShipOwnerComplaint(complaintRequestDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeFishingInstructorComplaint")
	public ResponseEntity<Complaint> writeFishingInstructorComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		return new ResponseEntity<Complaint>(complaintService.writeFishingInstructorComplaint(complaintRequestDTO), HttpStatus.CREATED);
	}

}
