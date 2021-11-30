package Team76.InternetSoftwareArchitecture.iservice;

import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.User;


public interface IConfirmationTokenService {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	ConfirmationToken saveConfirmationToken(User user);
}
