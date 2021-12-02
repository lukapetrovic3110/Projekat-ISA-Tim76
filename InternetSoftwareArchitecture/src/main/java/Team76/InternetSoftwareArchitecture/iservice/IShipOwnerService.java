package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.model.AccountApproval;
import Team76.InternetSoftwareArchitecture.model.User;

@Service
public interface IShipOwnerService {
	
	List<User> findAllShipOwnersByAccountApproval(AccountApproval accountApproval);
}
