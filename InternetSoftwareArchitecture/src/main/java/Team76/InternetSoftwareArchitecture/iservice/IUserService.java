package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AcceptRegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ChangePasswordDTO;
import Team76.InternetSoftwareArchitecture.dto.DeclineRegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestInstructorAndOwnerDTO;
import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.security.auth.JwtAuthenticationRequest;

@Service
public interface IUserService {
	User login(JwtAuthenticationRequest authenticationRequest);
	User saveClient(RegistrationRequestDTO userRequestDTO);
	User saveSystemAdministrator(RegistrationRequestDTO registrationRequestDTO);
	User saveCottageOwner(RegistrationRequestInstructorAndOwnerDTO userRequestDTO);
	User saveShipOwner(RegistrationRequestInstructorAndOwnerDTO userRequestDTO);
	User saveFishingInstructor(RegistrationRequestInstructorAndOwnerDTO userRequestDTO);
	void sendConfirmationEmail(User user, ConfirmationToken confirmationToken);
	User declineRegistarationRequest(DeclineRegistrationRequestDTO declineRegistrationRequestDTO);
	User acceptRegistrationRequest(AcceptRegistrationRequestDTO acceptRegistrationRequestDTO);
	User changePassword(ChangePasswordDTO changePasswordDTO);
	User findLoggedInUser();
	User changePasswordFirstLogin(ChangePasswordDTO changePasswordDTO);
}
