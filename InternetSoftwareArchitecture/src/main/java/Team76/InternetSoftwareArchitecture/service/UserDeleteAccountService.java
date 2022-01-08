package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.UserDeleteAccountRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.WaitingProfileDeleteRequestDTO;
import Team76.InternetSoftwareArchitecture.iservice.IUserDeleteAccountService;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.model.UserDeleteAccount;
import Team76.InternetSoftwareArchitecture.repository.IUserDeleteAccountRepository;
import Team76.InternetSoftwareArchitecture.repository.IUserRepository;

@Service
public class UserDeleteAccountService implements IUserDeleteAccountService {
	
	private IUserDeleteAccountRepository userDeleteAccountRepository;
	
	private IUserRepository userRepository;
	
	@Autowired
	public UserDeleteAccountService(IUserDeleteAccountRepository userDeleteAccountRepository, IUserRepository userRepository) {
		this.userDeleteAccountRepository = userDeleteAccountRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDeleteAccount sendDeleteClientAccountRequest(UserDeleteAccountRequestDTO userDeleteAccountDTO) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		UserDeleteAccount userDeleteAccount = new UserDeleteAccount();
		userDeleteAccount.setReason(userDeleteAccountDTO.getReason());
		userDeleteAccount.setUser(userRepository.findByUserId(user.getUserId()));
		return userDeleteAccountRepository.save(userDeleteAccount);
	}

	@Override
	public List<WaitingProfileDeleteRequestDTO> findProfileDeleteRequests() {
		List<UserDeleteAccount> userDeleteAccounts = userDeleteAccountRepository.findAll();
		List<WaitingProfileDeleteRequestDTO> requestDTOs = new ArrayList<WaitingProfileDeleteRequestDTO>();
		for (UserDeleteAccount userDeleteAccount : userDeleteAccounts) {
			User u = userRepository.findByUserId(userDeleteAccount.getUser().getUserId());
			requestDTOs.add(new WaitingProfileDeleteRequestDTO(u.getFirstName(), u.getLastName(), u.getEmail(), userDeleteAccount.getReason()));
		}
		
		return requestDTOs;
	}

}
