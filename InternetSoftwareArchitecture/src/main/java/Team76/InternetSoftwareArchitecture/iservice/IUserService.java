package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.RegistrationRequestDTO;
import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.security.auth.JwtAuthenticationRequest;

@Service
public interface IUserService {
	User login(JwtAuthenticationRequest authenticationRequest);
	User save(RegistrationRequestDTO userRequestDTO);
	void sendConfirmationEmail(User user, ConfirmationToken confirmationToken);
}