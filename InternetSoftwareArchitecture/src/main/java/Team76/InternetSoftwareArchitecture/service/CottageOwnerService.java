package Team76.InternetSoftwareArchitecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.ICottageOwnerService;
import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.model.UserType;
import Team76.InternetSoftwareArchitecture.repository.ICottageOwnerRepository;

@Service
public class CottageOwnerService implements ICottageOwnerService {
	
	private ICottageOwnerRepository cottageOwnerRepository;
	
	@Autowired
	public CottageOwnerService(ICottageOwnerRepository cottageOwnerRepository) {
		this.cottageOwnerRepository = cottageOwnerRepository;
	}

	@Override
	public List<CottageOwner> findAllCottageOwnersByAccountApproval(AccountApproval accountApproval, UserType userType) {
		return cottageOwnerRepository.findAllCottageOwnersByAccountApproval(accountApproval, userType);
	}

	@Override
	public CottageOwner findByEmail(String email) {
		return cottageOwnerRepository.findByEmail(email);
	}

	@Override
	public CottageOwner findCottageOwner() {
		CottageOwner cottageOwner = (CottageOwner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return cottageOwnerRepository.findByUserId(cottageOwner.getUserId());
	}
	
	@Override
	public CottageOwner update(CottageOwner cottageOwner) {
		CottageOwner existingCottageOwner = cottageOwnerRepository.findByUserId(cottageOwner.getUserId());
		existingCottageOwner.setFirstName(cottageOwner.getFirstName());
		existingCottageOwner.setLastName(cottageOwner.getLastName());
		existingCottageOwner.setAddress(cottageOwner.getAddress());
		existingCottageOwner.setPhoneNumber(cottageOwner.getPhoneNumber());
		
		return cottageOwnerRepository.save(existingCottageOwner);
	}

}
