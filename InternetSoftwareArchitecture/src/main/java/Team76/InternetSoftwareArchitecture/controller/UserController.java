package Team76.InternetSoftwareArchitecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.service.UserService;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
	}

}
