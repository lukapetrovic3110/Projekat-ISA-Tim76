package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.FishingInstructorSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.model.Client;

@Service
public interface IClientService {
	
	Client update(Client client);

	Client findClient();
	
	List<FishingInstructorSubscriptionDTO> getFishingInstructorSubscriptions();
	
	Boolean unsubscribeFishingInstructor(Long id);
	
	Boolean unsubscribeCottage(Long id);

	Boolean unsubscribeShip(Long id);
}
