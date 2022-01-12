package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.CottageSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.dto.FishingInstructorSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.dto.ShipSubscriptionDTO;
import Team76.InternetSoftwareArchitecture.iservice.IClientService;
import Team76.InternetSoftwareArchitecture.model.Address;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Cottage;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.Ship;
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

	public List<FishingInstructorSubscriptionDTO> getFishingInstructorSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<FishingInstructor> fishingInstructorSubscriptions =  client.getFishingInstructorSubscriptions();
		List<FishingInstructorSubscriptionDTO> fishingInstructorSubscriptionDTOs = new ArrayList<FishingInstructorSubscriptionDTO>();
		for (FishingInstructor f : fishingInstructorSubscriptions)
			fishingInstructorSubscriptionDTOs.add(new FishingInstructorSubscriptionDTO(f.getFirstName(), f.getLastName(), f.getEmail(), f.getPhoneNumber()));
		return fishingInstructorSubscriptionDTOs;
	}

	public List<ShipSubscriptionDTO> getShipSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Ship> shipSubscriptions =  client.getShipSubsriptions();
		List<ShipSubscriptionDTO> shipSubscriptionDTOs = new ArrayList<ShipSubscriptionDTO>();
		for (Ship s : shipSubscriptions)
			shipSubscriptionDTOs.add(new ShipSubscriptionDTO(s.getName(), mapAddress(s.getAddress()), s.getDescription(), s.getCapacity(), s.getShipType(), s.getShipOwner().getFirstName(), s.getShipOwner().getLastName(), s.getShipOwner().getEmail(), s.getShipOwner().getPhoneNumber()));
		return shipSubscriptionDTOs;
	}

	private String mapAddress(Address a) {
		StringBuilder address = new StringBuilder();
		address.append(a.getStreet());
		address.append(" ");
		address.append(a.getStreetNumber());
		address.append(" ");
		address.append(a.getCity());
		address.append(", ");
		address.append(a.getCountry());
		return address.toString();
	}

	public List<CottageSubscriptionDTO> getCottageSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Cottage> cottageSubsciptions = client.getCottageSubscriptions();
		List<CottageSubscriptionDTO> cottageSubscriptionDTOs = new ArrayList<CottageSubscriptionDTO>();
		for (Cottage c : cottageSubsciptions) 
			cottageSubscriptionDTOs.add(new CottageSubscriptionDTO(c.getName(), mapAddress(c.getAddress()), c.getDescription(), c.getNumberOfRooms(), c.getNumberOfBedsPerRoom(), c.getCottageOwner().getLastName(), c.getCottageOwner().getLastName(), c.getCottageOwner().getEmail(), c.getCottageOwner().getPhoneNumber()));
		return cottageSubscriptionDTOs;
	}

}
