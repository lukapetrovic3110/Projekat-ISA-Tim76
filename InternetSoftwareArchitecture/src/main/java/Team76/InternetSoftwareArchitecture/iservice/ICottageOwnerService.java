package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.CottageOwner;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Service
public interface ICottageOwnerService {
	
	List<CottageOwner> findAllCottageOwnersByAccountApproval(AccountApproval accountApproval, UserType userType);
	
	CottageOwner findByEmail(String email);
	
	CottageOwner findCottageOwner();
	
	CottageOwner update(CottageOwner cottageOwner);
}
