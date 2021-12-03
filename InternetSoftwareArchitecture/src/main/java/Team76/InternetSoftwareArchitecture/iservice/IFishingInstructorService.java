package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.FishingInstructor;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Service
public interface IFishingInstructorService {

	List<FishingInstructor> findAllFishingInstructorsByAccountApproval(AccountApproval accountApproval, UserType userType);
	
	FishingInstructor findByEmail(String email);
}
