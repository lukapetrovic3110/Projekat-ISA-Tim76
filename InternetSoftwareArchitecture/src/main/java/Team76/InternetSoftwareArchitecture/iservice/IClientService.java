package Team76.InternetSoftwareArchitecture.iservice;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.Client;

@Service
public interface IClientService {
	// Client findByUserId(Long userId);
	Client update(Client client);

	Client findClient();
}
