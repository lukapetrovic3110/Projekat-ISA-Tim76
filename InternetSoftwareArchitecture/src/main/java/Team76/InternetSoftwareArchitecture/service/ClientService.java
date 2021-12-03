package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IClientService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.repository.IClientRepository;

@Service
public class ClientService implements IClientService {

	private IClientRepository clientRepository;
	
	@Autowired
	public ClientService(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client findByUserId(Long userId) {
		return clientRepository.findByUserId(userId);
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

}