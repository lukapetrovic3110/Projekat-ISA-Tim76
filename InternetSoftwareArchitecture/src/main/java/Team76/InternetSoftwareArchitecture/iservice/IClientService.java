package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.SubscriptionDTO;
import Team76.InternetSoftwareArchitecture.model.Client;

@Service
public interface IClientService {
	
	Client update(Client client);

	Client findClient();
	
	List<SubscriptionDTO> getClientSubscriptions();
}
