package Team76.InternetSoftwareArchitecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IShipOwnerService;
import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.repository.IShipOwnerRepository;

@Service
public class ShipOwnerService implements IShipOwnerService {
	
	private IShipOwnerRepository shipOwnerRepository;
	
	@Autowired
	public ShipOwnerService(IShipOwnerRepository shipOwnerRepository) {
		this.shipOwnerRepository = shipOwnerRepository;
	}

	@Override
	public List<ShipOwner> findAllShipOwnersByAccountApproval(AccountApproval accountApproval, UserType userType) {
		return shipOwnerRepository.findAllShipOwnersByAccountApproval(accountApproval, userType);		
	}

	@Override
	public ShipOwner findByEmail(String email) {
		return shipOwnerRepository.findByEmail(email);
	}

	@Override
	public ShipOwner findShipOwner() {
		ShipOwner shipOwner = (ShipOwner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return shipOwnerRepository.findByUserId(shipOwner.getUserId());
	}

	@Override
	public ShipOwner update(ShipOwner shipOwner) {
		ShipOwner existingShipOwner = shipOwnerRepository.findByUserId(shipOwner.getUserId());
		existingShipOwner.setFirstName(shipOwner.getFirstName());
		existingShipOwner.setLastName(shipOwner.getLastName());
		existingShipOwner.setAddress(shipOwner.getAddress());
		existingShipOwner.setPhoneNumber(shipOwner.getPhoneNumber());
		
		return shipOwnerRepository.save(existingShipOwner);
	}

}
