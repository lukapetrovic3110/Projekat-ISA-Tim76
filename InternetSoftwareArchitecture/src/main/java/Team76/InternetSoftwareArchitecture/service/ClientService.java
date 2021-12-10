package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.SubscriptionDTO;
import Team76.InternetSoftwareArchitecture.iservice.IClientService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.repository.IClientRepository;

@Service
public class ClientService implements IClientService {

	private IClientRepository clientRepository;
	
	@Autowired
	public ClientService(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client findClient() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return clientRepository.findByUserId(client.getUserId());
	}
	
	@Override
	public Client update(Client client) {
		Client existingClient = clientRepository.findByUserId(client.getUserId());
		existingClient.setFirstName(client.getFirstName());
		existingClient.setLastName(client.getLastName());
		existingClient.setAddress(client.getAddress());
		existingClient.setPhoneNumber(client.getPhoneNumber());
		return clientRepository.save(existingClient);
	}

	public List<SubscriptionDTO> getClientSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<User> subscriptions =  client.getSubscriptions();
		List<SubscriptionDTO> subscriptionDTOs = new ArrayList<SubscriptionDTO>();
		for (User user : subscriptions) {
			subscriptionDTOs.add(new SubscriptionDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber()));
		}
		
		return subscriptionDTOs;
	}

}
