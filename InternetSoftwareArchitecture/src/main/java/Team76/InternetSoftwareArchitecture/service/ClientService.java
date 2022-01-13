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
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.IFishingInstructorRepository;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;

@Service
public class ClientService implements IClientService {

	private IClientRepository clientRepository;
	private IFishingInstructorRepository fishingInstructorRepository;
	private ICottageRepository cottageRepository;
	private IShipRepository shipRepository;
	
	@Autowired
	public ClientService(IClientRepository clientRepository, IFishingInstructorRepository fishingInstructorRepository, ICottageRepository cottageRepository, IShipRepository shipRepository) {
		this.clientRepository = clientRepository;
		this.fishingInstructorRepository = fishingInstructorRepository;
		this.cottageRepository = cottageRepository;
		this.shipRepository = shipRepository;
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

	@Override
	public List<FishingInstructorSubscriptionDTO> getFishingInstructorSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<FishingInstructor> fishingInstructorSubscriptions =  client.getFishingInstructorSubscriptions();
		List<FishingInstructorSubscriptionDTO> fishingInstructorSubscriptionDTOs = new ArrayList<FishingInstructorSubscriptionDTO>();
		for (FishingInstructor f : fishingInstructorSubscriptions)
			fishingInstructorSubscriptionDTOs.add(new FishingInstructorSubscriptionDTO(f.getUserId(), f.getFirstName(), f.getLastName(), f.getEmail(), f.getPhoneNumber()));
		return fishingInstructorSubscriptionDTOs;
	}
	
	
	@Override
	public Boolean unsubscribeFishingInstructor(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<FishingInstructor> fishingInstructorSubscriptions =  client.getFishingInstructorSubscriptions();
		try {
			for (FishingInstructor f : fishingInstructorSubscriptions)
				if (f.getUserId() == id)
					fishingInstructorSubscriptions.remove(f);
			clientRepository.save(client);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean subscribeFishingInstructor(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<FishingInstructor> fishingInstructorSubscriptions =  client.getFishingInstructorSubscriptions();
		try {
			fishingInstructorSubscriptions.add(fishingInstructorRepository.findByUserId(id));
			clientRepository.save(client);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<ShipSubscriptionDTO> getShipSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Ship> shipSubscriptions =  client.getShipSubsriptions();
		List<ShipSubscriptionDTO> shipSubscriptionDTOs = new ArrayList<ShipSubscriptionDTO>();
		for (Ship s : shipSubscriptions)
			shipSubscriptionDTOs.add(new ShipSubscriptionDTO(s.getShipId(), s.getName(), mapAddress(s.getAddress()), s.getDescription(), s.getCapacity(), s.getShipType(), s.getShipOwner().getFirstName(), s.getShipOwner().getLastName(), s.getShipOwner().getEmail(), s.getShipOwner().getPhoneNumber()));
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

	@Override
	public List<CottageSubscriptionDTO> getCottageSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Cottage> cottageSubsciptions = client.getCottageSubscriptions();
		List<CottageSubscriptionDTO> cottageSubscriptionDTOs = new ArrayList<CottageSubscriptionDTO>();
		for (Cottage c : cottageSubsciptions) 
			cottageSubscriptionDTOs.add(new CottageSubscriptionDTO(c.getCottageId(), c.getName(), mapAddress(c.getAddress()), c.getDescription(), c.getNumberOfRooms(), c.getNumberOfBedsPerRoom(), c.getCottageOwner().getLastName(), c.getCottageOwner().getLastName(), c.getCottageOwner().getEmail(), c.getCottageOwner().getPhoneNumber()));
		return cottageSubscriptionDTOs;
	}
	
	
	@Override
	public Boolean unsubscribeShip(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Ship> shipSubscriptions =  client.getShipSubsriptions();
		try {
			for (Ship s : shipSubscriptions)
				if (s.getShipId() == id)
					shipSubscriptions.remove(s);
			clientRepository.save(client);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Boolean subscribeShip(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Ship> shipSubscriptions =  client.getShipSubsriptions();
		try {
			shipSubscriptions.add(shipRepository.findById(id).get());
			clientRepository.save(client);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	
	@Override
	public Boolean unsubscribeCottage(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Cottage> cottageSubsciptions = client.getCottageSubscriptions();
		try {
			for (Cottage c : cottageSubsciptions)
				if (c.getCottageId() == id)
					cottageSubsciptions.remove(c);
			clientRepository.save(client);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean subscribeCottage(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Cottage> cottageSubscriptions =  client.getCottageSubscriptions();
		try {
			cottageSubscriptions.add(cottageRepository.findById(id).get());
			clientRepository.save(client);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
