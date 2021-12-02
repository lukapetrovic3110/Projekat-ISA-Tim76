package Team76.InternetSoftwareArchitecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.ICottageOwnerService;
import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.repository.ICottageOwnerRepository;

@Service
public class CottageOwnerService implements ICottageOwnerService {
	
	private ICottageOwnerRepository cottageOwnerRepository;
	
	@Autowired
	public CottageOwnerService(ICottageOwnerRepository cottageOwnerRepository) {
		this.cottageOwnerRepository = cottageOwnerRepository;
	}

	@Override
	public List<User> findAllCottageOwnersByAccountApproval(AccountApproval accountApproval) {
		return cottageOwnerRepository.findAllCottageOwnersByAccountApproval(accountApproval);
	}

}
