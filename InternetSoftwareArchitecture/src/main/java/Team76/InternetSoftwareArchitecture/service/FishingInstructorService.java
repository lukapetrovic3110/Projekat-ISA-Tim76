package Team76.InternetSoftwareArchitecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.iservice.IFishingInstructorService;
import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.repository.IFishingInstructorRepository;

@Service
public class FishingInstructorService implements IFishingInstructorService {
	
	private IFishingInstructorRepository fishingInstructorRepository;
	
	@Autowired
	public FishingInstructorService(IFishingInstructorRepository fishingInstructorRepository) {
		this.fishingInstructorRepository = fishingInstructorRepository;
	}

	@Override
	public List<User> findAllFishingInstructorsByAccountApproval(AccountApproval accountApproval) {
		return fishingInstructorRepository.findAllFishingInstructorsByAccountApproval(accountApproval);
	}

}
