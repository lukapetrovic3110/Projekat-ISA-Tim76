package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IConfirmationTokenService;
import Team76.InternetSoftwareArchitecture.model.ConfirmationToken;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.repository.IConfirmationTokenRepository;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {

	private final IConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	public ConfirmationTokenService(IConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	@Override
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	}

	@Override
	public ConfirmationToken saveConfirmationToken(User user) {
		return confirmationTokenRepository.save(new ConfirmationToken(user));
	}

}
