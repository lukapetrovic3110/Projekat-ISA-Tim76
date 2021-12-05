package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.User;

@Service
public interface IConfirmationTokenService {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	ConfirmationToken saveConfirmationToken(User user);
}
