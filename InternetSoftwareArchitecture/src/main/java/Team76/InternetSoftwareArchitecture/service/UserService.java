package Team76.InternetSoftwareArchitecture.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestInstructorAndOwnerDTO;
import Team76.InternetSoftwareArchitecture.iservice.IUserService;
import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.Authority;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.repository.IUserRepository;
import Team76.InternetSoftwareArchitecture.security.auth.JwtAuthenticationRequest;

@Service
public class UserService implements IUserService {

	private IUserRepository userRepository;

	private AuthorityService authorityService;

	private ConfirmationTokenService confirmationTokenService;

	private EmailService emailService;

	private AddressService addressService;

	@Autowired
	public UserService(IUserRepository userRepository, AuthorityService authorityService,
			ConfirmationTokenService confirmationTokenService, EmailService emailService,
			AddressService addressService) {
		this.userRepository = userRepository;
		this.authorityService = authorityService;
		this.confirmationTokenService = confirmationTokenService;
		this.emailService = emailService;
		this.addressService = addressService;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveClient(RegistrationRequestDTO userRequestDTO) {
		Client client = new Client();
		client.setEmail(userRequestDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		client.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(userRequestDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		client.setPassword(securePassword);
		client.setFirstName(userRequestDTO.getFirstName());
		client.setLastName(userRequestDTO.getLastName());
		client.setPhoneNumber(userRequestDTO.getPhoneNumber());
		client.setAddress(userRequestDTO.getAddress());
		addressService.saveAddress(userRequestDTO.getAddress());
		client.setUserType(UserType.CLIENT);
		client.setEnabled(false);
		Set<Authority> authorities = authorityService.findByName("ROLE_CLIENT");
		client.setAuthorities(authorities);
		userRepository.save(client);
		ConfirmationToken confirmationToken = confirmationTokenService.saveConfirmationToken(client);
		sendConfirmationEmail(client, confirmationToken);
		return client;
	}
	
	@Override
	public User saveCottageOwner(RegistrationRequestInstructorAndOwnerDTO userRequestDTO) {
		CottageOwner cottageOwner = new CottageOwner();
		cottageOwner.setEmail(userRequestDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		cottageOwner.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(userRequestDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		cottageOwner.setPassword(securePassword);
		cottageOwner.setFirstName(userRequestDTO.getFirstName());
		cottageOwner.setLastName(userRequestDTO.getLastName());
		cottageOwner.setPhoneNumber(userRequestDTO.getPhoneNumber());
		cottageOwner.setAddress(userRequestDTO.getAddress());
		addressService.saveAddress(userRequestDTO.getAddress());
		cottageOwner.setUserType(UserType.COTTAGE_OWNER);
		cottageOwner.setEnabled(false);
		Set<Authority> authorities = authorityService.findByName("ROLE_COTTAGE_OWNER");
		cottageOwner.setAuthorities(authorities);
		cottageOwner.setExplanation(userRequestDTO.getExplanation());
		cottageOwner.setAccountApproval(AccountApproval.WAITING);
		userRepository.save(cottageOwner);
		return cottageOwner;
	}
	
	@Override
	public User saveShipOwner(RegistrationRequestInstructorAndOwnerDTO userRequestDTO) {
		ShipOwner shipOwner = new ShipOwner();
		shipOwner.setEmail(userRequestDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		shipOwner.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(userRequestDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		shipOwner.setPassword(securePassword);
		shipOwner.setFirstName(userRequestDTO.getFirstName());
		shipOwner.setLastName(userRequestDTO.getLastName());
		shipOwner.setPhoneNumber(userRequestDTO.getPhoneNumber());
		shipOwner.setAddress(userRequestDTO.getAddress());
		addressService.saveAddress(userRequestDTO.getAddress());
		shipOwner.setUserType(UserType.SHIP_OWNER);
		shipOwner.setEnabled(false);
		Set<Authority> authorities = authorityService.findByName("ROLE_SHIP_OWNER");
		shipOwner.setAuthorities(authorities);
		shipOwner.setExplanation(userRequestDTO.getExplanation());
		shipOwner.setAccountApproval(AccountApproval.WAITING);
		userRepository.save(shipOwner);
		return shipOwner;
	}
	
	@Override
	public User saveFishingInstructor(RegistrationRequestInstructorAndOwnerDTO userRequestDTO) {
		FishingInstructor fishingInstructor = new FishingInstructor();
		fishingInstructor.setEmail(userRequestDTO.getEmail());
		byte[] salt = generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		fishingInstructor.setSalt(encodedSalt);
		String passwordWithSalt = generatePasswordWithSalt(userRequestDTO.getPassword(), encodedSalt);
		String securePassword = hashPassword(passwordWithSalt);
		fishingInstructor.setPassword(securePassword);
		fishingInstructor.setFirstName(userRequestDTO.getFirstName());
		fishingInstructor.setLastName(userRequestDTO.getLastName());
		fishingInstructor.setPhoneNumber(userRequestDTO.getPhoneNumber());
		fishingInstructor.setAddress(userRequestDTO.getAddress());
		addressService.saveAddress(userRequestDTO.getAddress());
		fishingInstructor.setUserType(UserType.FISHING_INSTRUCTOR);
		fishingInstructor.setEnabled(false);
		Set<Authority> authorities = authorityService.findByName("ROLE_FISHING_INSTRUCTOR");
		fishingInstructor.setAuthorities(authorities);
		fishingInstructor.setExplanation(userRequestDTO.getExplanation());
		fishingInstructor.setAccountApproval(AccountApproval.WAITING);
		userRepository.save(fishingInstructor);
		return fishingInstructor;
	}

	@Override
	public User login(JwtAuthenticationRequest authenticationRequest) {
		User user = findByEmail(authenticationRequest.getEmail());
		if (user != null)
			if (verifyHash(generatePasswordWithSalt(authenticationRequest.getPassword(), user.getSalt()),
					user.getPassword()))
				return user;

		return null;
	}

	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] genSalt = new byte[16];
		random.nextBytes(genSalt);
		return genSalt;
	}

	private String generatePasswordWithSalt(String userPassword, String salt) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(userPassword);
		stringBuilder.append(salt);
		return stringBuilder.toString();
	}

	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

	@Override
	public void sendConfirmationEmail(User user, ConfirmationToken confirmationToken) {
		System.out.println("User's email: " + user.getEmail());
		try {

			String recipientEmail = user.getEmail();
			String subject = "Confirm registration";
			String message = "Please confirm your registration by clicking the link below \n\n\n"
					+ "http://localhost:8083/confirmRegistration/" + confirmationToken.getConfirmationToken();
			emailService.sendNotificaitionAsync(recipientEmail, subject, message);
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
		}

	}

	public void accountConfirmation(User user) {
		User existing = userRepository.findById(user.getUserId()).orElse(null);
		existing.setEnabled(true);
		userRepository.save(existing);
	}

}
