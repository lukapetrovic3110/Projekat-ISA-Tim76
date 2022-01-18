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

import Team76.InternetSoftwareArchitecture.dto.AnswerComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ObjectComplaintDTO;
import Team76.InternetSoftwareArchitecture.dto.UserComplaintDTO;
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
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/cottage")
	public ResponseEntity<List<ObjectComplaintDTO>> getCottageComplaints() {
		return new ResponseEntity<List<ObjectComplaintDTO>>(complaintService.getCottageComplaints(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/ship")
	public ResponseEntity<List<ObjectComplaintDTO>> getShipComplaints() {
		return new ResponseEntity<List<ObjectComplaintDTO>>(complaintService.getShipComplaints(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping(value = "/user")
	public ResponseEntity<List<UserComplaintDTO>> getUserComplaints() {
		return new ResponseEntity<List<UserComplaintDTO>>(complaintService.getUserComplaints(), HttpStatus.OK);
	}	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeCottageComplaint")
	public ResponseEntity<?> writeCottageComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		try {
			if (complaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(complaintRequestDTO.getComment().length() > 50)
				throw new Exception("The comment is long please enter up to 50 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(complaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Complaint>(complaintService.writeCottageComplaint(complaintRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeCottageOwnerComplaint")
	public ResponseEntity<?> writeCottageOwnerComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		try {
			if (complaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(complaintRequestDTO.getComment().length() > 50)
				throw new Exception("The comment is long please enter up to 50 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(complaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Complaint>(complaintService.writeUserComplaint(complaintRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeShipComplaint")
	public ResponseEntity<?> writeShipComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		try {
			if (complaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(complaintRequestDTO.getComment().length() > 50)
				throw new Exception("The comment is long please enter up to 50 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(complaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Complaint>(complaintService.writeShipComplaint(complaintRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeShipOwnerComplaint")
	public ResponseEntity<?> writeShipOwnerComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		try {
			if (complaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(complaintRequestDTO.getComment().length() > 50)
				throw new Exception("The comment is long please enter up to 50 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(complaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Complaint>(complaintService.writeUserComplaint(complaintRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/writeFishingInstructorComplaint")
	public ResponseEntity<?> writeFishingInstructorComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) {
		try {
			if (complaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(complaintRequestDTO.getComment().length() > 50)
				throw new Exception("The comment is long please enter up to 50 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(complaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Complaint>(complaintService.writeUserComplaint(complaintRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/acceptCottageComplaintRequest")
	public ResponseEntity<?> acceptCottageComplaintRequest(@RequestBody AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		try {
			if (answerComplaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerComplaintRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerComplaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(complaintService.accpetCottageComplaintRequest(answerComplaintRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/declineCottageComplaintRequest")
	public ResponseEntity<?> declineCottageComplaintRequest(@RequestBody AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		try {
			if (answerComplaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerComplaintRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerComplaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(complaintService.declineCottageComplaintRequest(answerComplaintRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/acceptShipComplaintRequest")
	public ResponseEntity<?> acceptShipComplaintRequest(@RequestBody AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		try {
			if (answerComplaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerComplaintRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerComplaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(complaintService.acceptShipComplaintRequest(answerComplaintRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/declineShipComplaintRequest")
	public ResponseEntity<?> declineShipComplaintRequest(@RequestBody AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		try {
			if (answerComplaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerComplaintRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerComplaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(complaintService.declineShipComplaintRequest(answerComplaintRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/acceptUserComplaintRequest")
	public ResponseEntity<?> acceptUserComplaintRequest(@RequestBody AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		try {
			if (answerComplaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerComplaintRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerComplaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(complaintService.acceptUserComplaintRequest(answerComplaintRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PostMapping("/declineUserComplaintRequest")
	public ResponseEntity<?> declineUserComplaintRequest(@RequestBody AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		try {
			if (answerComplaintRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerComplaintRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerComplaintRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(complaintService.declineUserComplaintRequest(answerComplaintRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
	
	
}
