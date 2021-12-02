package Team76.InternetSoftwareArchitecture.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestInstructorAndOwnerDTO;
import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.model.UserTokenState;
import Team76.InternetSoftwareArchitecture.security.TokenUtils;
import Team76.InternetSoftwareArchitecture.security.auth.JwtAuthenticationRequest;
import Team76.InternetSoftwareArchitecture.service.ConfirmationTokenService;
import Team76.InternetSoftwareArchitecture.service.UserService;
import Team76.InternetSoftwareArchitecture.service.CustomUserDetailsService;


@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthentificationController {
	
	
	private TokenUtils tokenUtils;
	
	private UserService userService;
	
	private AuthenticationManager authenticationManager;
	
	private ConfirmationTokenService confirmationTokenService;
	
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	public AuthentificationController(TokenUtils tokenUtils, UserService userService,
			AuthenticationManager authenticationManager, ConfirmationTokenService confirmationTokenService, CustomUserDetailsService userDetailsService) {
		super();
		this.tokenUtils = tokenUtils;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.confirmationTokenService = confirmationTokenService;
		this.userDetailsService = userDetailsService;
	}
	
	@PostMapping("/signupClient")
	public ResponseEntity<?> signupClient(@RequestBody RegistrationRequestDTO userRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
		try {
			User existUser = userService.findByEmail(userRequestDTO.getEmail());
			if (existUser != null)
				throw new Exception( "Email already exists.");

			return new ResponseEntity<>(userService.saveClient(userRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signupCottageOwner")
	public ResponseEntity<?> signupCottageOwner(@RequestBody RegistrationRequestInstructorAndOwnerDTO userRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
		try {
			User existUser = userService.findByEmail(userRequestDTO.getEmail());
			if (existUser != null)
				throw new Exception( "Email already exists.");

			return new ResponseEntity<>(userService.saveCottageOwner(userRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signupShipOwner")
	public ResponseEntity<?> signupShipOwner(@RequestBody RegistrationRequestInstructorAndOwnerDTO userRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
		try {
			User existUser = userService.findByEmail(userRequestDTO.getEmail());
			if (existUser != null)
				throw new Exception( "Email already exists.");

			return new ResponseEntity<>(userService.saveShipOwner(userRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signupFishingInstructor")
	public ResponseEntity<?> signupFishingInstructor(@RequestBody RegistrationRequestInstructorAndOwnerDTO userRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
		try {
			User existUser = userService.findByEmail(userRequestDTO.getEmail());
			if (existUser != null)
				throw new Exception( "Email already exists.");

			return new ResponseEntity<>(userService.saveFishingInstructor(userRequestDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		try {
			User logInUser = userService.login(authenticationRequest);
			StringBuilder passwordWithSalt = new StringBuilder();
			passwordWithSalt.append(authenticationRequest.getPassword());
			passwordWithSalt.append(logInUser.getSalt());

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), passwordWithSalt.toString()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			User user = (User) authentication.getPrincipal();

			if (user.getEnabled()) {
				String jwt = tokenUtils.generateToken(authenticationRequest.getEmail());
				int expiresIn = tokenUtils.getExpiredIn();
				return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user));
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/confirm_account/{token}", consumes = "application/json")
	public ResponseEntity<Boolean> confirmAccount(@PathVariable String token) {

		try {

			ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
			if (confirmationToken != null
					&& LocalDateTime.now().isBefore(confirmationToken.getCreatedDate().plusDays(5))) {
				User user = userService.findByEmail(confirmationToken.getUser().getEmail());
				System.out.println(user.toString());
				userService.accountConfirmation(user);
				
				return new ResponseEntity<>(HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastResetPasswordDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn, user));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

}
