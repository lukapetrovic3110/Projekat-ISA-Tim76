package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.ShipOwner;
import Team76.InternetSoftwareArchitecture.model.UserType;

@Service
public interface IShipOwnerService {
	
	List<ShipOwner> findAllShipOwnersByAccountApproval(AccountApproval accountApproval, UserType userType);
	
	ShipOwner findByEmail(String email);
	
	ShipOwner findShipOwner();
	
	ShipOwner update(ShipOwner shipOwner);
	
}
