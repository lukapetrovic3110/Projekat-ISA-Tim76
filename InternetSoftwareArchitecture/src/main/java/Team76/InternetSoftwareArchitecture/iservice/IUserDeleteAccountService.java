package Team76.InternetSoftwareArchitecture.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.UserDeleteAccountRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.WaitingProfileDeleteRequestDTO;
import Team76.InternetSoftwareArchitecture.model.UserDeleteAccount;

@Service
public interface IUserDeleteAccountService {
	
	UserDeleteAccount sendDeleteClientAccountRequest(UserDeleteAccountRequestDTO userDeleteAccountDTO);
	List<WaitingProfileDeleteRequestDTO> findProfileDeleteRequests();
}
