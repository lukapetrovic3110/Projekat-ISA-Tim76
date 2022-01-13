package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.HashSet;
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
		Set<FishingInstructor> fishingInstructorSubscriptions = new HashSet<FishingInstructor>();
		List<FishingInstructorSubscriptionDTO> fishingInstructorSubscriptionDTOs = new ArrayList<FishingInstructorSubscriptionDTO>();
		if(!client.getFishingInstructorSubscriptions().isEmpty()) {
			fishingInstructorSubscriptions = client.getFishingInstructorSubscriptions();
			for (FishingInstructor f : fishingInstructorSubscriptions)
				fishingInstructorSubscriptionDTOs.add(new FishingInstructorSubscriptionDTO(f.getUserId(), f.getFirstName(), f.getLastName(), f.getEmail(), f.getPhoneNumber()));
		}
		return fishingInstructorSubscriptionDTOs;
	}
	
	@Override
	public List<ShipSubscriptionDTO> getShipSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Ship> shipSubscriptions = new HashSet<Ship>(); 
		List<ShipSubscriptionDTO> shipSubscriptionDTOs = new ArrayList<ShipSubscriptionDTO>();
		if(!client.getShipSubscriptions().isEmpty()) {
			shipSubscriptions = client.getShipSubscriptions();
			for (Ship s : shipSubscriptions)
				shipSubscriptionDTOs.add(new ShipSubscriptionDTO(s.getShipId(), s.getName(), mapAddress(s.getAddress()), s.getDescription(), s.getCapacity(), s.getShipType(), s.getShipOwner().getFirstName(), s.getShipOwner().getLastName(), s.getShipOwner().getEmail(), s.getShipOwner().getPhoneNumber()));
		}
		return shipSubscriptionDTOs;
	}
	
	@Override
	public List<CottageSubscriptionDTO> getCottageSubscriptions() {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Cottage> cottageSubsciptions = new HashSet<Cottage>();
		List<CottageSubscriptionDTO> cottageSubscriptionDTOs = new ArrayList<CottageSubscriptionDTO>();
		if(!client.getCottageSubscriptions().isEmpty()) {
			cottageSubsciptions = client.getCottageSubscriptions();
			for (Cottage c : cottageSubsciptions) 
				cottageSubscriptionDTOs.add(new CottageSubscriptionDTO(c.getCottageId(), c.getName(), mapAddress(c.getAddress()), c.getDescription(), c.getNumberOfRooms(), c.getNumberOfBedsPerRoom(), c.getCottageOwner().getLastName(), c.getCottageOwner().getLastName(), c.getCottageOwner().getEmail(), c.getCottageOwner().getPhoneNumber()));
		}
		return cottageSubscriptionDTOs;
	}
	
	
	@Override
	public Boolean unsubscribeFishingInstructor(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<FishingInstructor> fishingInstructorSubscriptions = new HashSet<FishingInstructor>();
		try {
			if(!client.getFishingInstructorSubscriptions().isEmpty()) {
				fishingInstructorSubscriptions = client.getFishingInstructorSubscriptions();
				FishingInstructor f = fishingInstructorRepository.findByUserId(id);
				for(FishingInstructor fishingInstructor : fishingInstructorSubscriptions) {
					if(fishingInstructor.getUserId() == f.getUserId()) {
						fishingInstructorSubscriptions.remove(fishingInstructor);
						break;
					}
				}
				client.setFishingInstructorSubscriptions(fishingInstructorSubscriptions);
				clientRepository.save(client);
				return true;
			}
			return false;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean unsubscribeShip(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Ship> shipSubscriptions = new HashSet<Ship>();
		try {	
			if(!client.getShipSubscriptions().isEmpty()) {
				shipSubscriptions = client.getShipSubscriptions();
				Ship s = shipRepository.findById(id).get();
				for(Ship ship : shipSubscriptions) {
					if(ship.getShipId() == s.getShipId()) {
						shipSubscriptions.remove(ship);
						break;
					}
				}
				client.setShipSubscriptions(shipSubscriptions);
				clientRepository.save(client);
				return true;
			}
			return false;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean unsubscribeCottage(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Cottage> cottageSubscriptions = new HashSet<Cottage>();
		try {
			if(!client.getShipSubscriptions().isEmpty()) {
				cottageSubscriptions = client.getCottageSubscriptions();
				Cottage c = cottageRepository.findById(id).get();
				for(Cottage cottage : cottageSubscriptions) {
					if(cottage.getCottageId() == c.getCottageId()) {
						cottageSubscriptions.remove(cottage);
						break;
					}
				}
				client.setCottageSubscriptions(cottageSubscriptions);
				clientRepository.save(client);
				return true;
			}
			return false;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean subscribeFishingInstructor(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			fishingInstructorRepository.addNewFishingInstructorSubscriptions(client.getUserId(), id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean subscribeShip(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			shipRepository.addNewShipSubscriptions(client.getUserId(), id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@Override
	public Boolean subscribeCottage(Long id) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			cottageRepository.addNewCottageSubscriptions(client.getUserId(), id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
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
	
}
