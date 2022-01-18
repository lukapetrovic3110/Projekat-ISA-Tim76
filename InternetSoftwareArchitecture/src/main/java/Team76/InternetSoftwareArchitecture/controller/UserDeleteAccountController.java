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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.dto.AnswerProfileDeleteRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.UserDeleteAccountRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.WaitingProfileDeleteRequestDTO;
import Team76.InternetSoftwareArchitecture.model.UserDeleteAccount;
import Team76.InternetSoftwareArchitecture.service.UserDeleteAccountService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/deleteAccount", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserDeleteAccountController {
	
	private UserDeleteAccountService userDeleteAccountService;
	
	@Autowired
	public UserDeleteAccountController(UserDeleteAccountService userDeleteAccountService) {
		this.userDeleteAccountService = userDeleteAccountService;
	}
		
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/client")
	public ResponseEntity<?> sendDeleteClientAccountRequest(@RequestBody UserDeleteAccountRequestDTO userDeleteAccountDTO) {
		try {
			if (userDeleteAccountDTO.getReason().trim().isEmpty())
				throw new Exception("Please enter a reason!");
			else if(userDeleteAccountDTO.getReason().length() > 100)
				throw new Exception("The reason is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(userDeleteAccountDTO.getReason()).find())
				throw new Exception("The reason shouldn't contain special characters.");
			else
				return new ResponseEntity<UserDeleteAccount>(userDeleteAccountService.sendDeleteClientAccountRequest(userDeleteAccountDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping("/profileDeleteRequests")
	public ResponseEntity<List<WaitingProfileDeleteRequestDTO>> findProfileDeleteRequests() {
		return new ResponseEntity<List<WaitingProfileDeleteRequestDTO>>(userDeleteAccountService.findProfileDeleteRequests(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PutMapping("/acceptRequest")
	public ResponseEntity<?> acceptRequest(@RequestBody AnswerProfileDeleteRequestDTO answerProfileDeleteRequestDTO) {
		try {
			if (answerProfileDeleteRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerProfileDeleteRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerProfileDeleteRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(userDeleteAccountService.accpetRequest(answerProfileDeleteRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@PutMapping("/declineRequest")
	public ResponseEntity<?> declineRequest(@RequestBody AnswerProfileDeleteRequestDTO answerProfileDeleteRequestDTO) {
		try {
			if (answerProfileDeleteRequestDTO.getComment().trim().isEmpty())
				throw new Exception("Please enter a comment!");
			else if(answerProfileDeleteRequestDTO.getComment().length() > 100)
				throw new Exception("The comment is long please enter up to 100 characters!");
			else if(Pattern.compile("[#$%^&*'<>/]+").matcher(answerProfileDeleteRequestDTO.getComment()).find())
				throw new Exception("The comment shouldn't contain special characters.");
			else
				return new ResponseEntity<Boolean>(userDeleteAccountService.declineRequest(answerProfileDeleteRequestDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
