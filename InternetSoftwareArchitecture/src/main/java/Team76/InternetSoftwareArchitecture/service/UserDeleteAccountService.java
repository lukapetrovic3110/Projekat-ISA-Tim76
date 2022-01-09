package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AnswerProfileDeleteRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.UserDeleteAccountRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.WaitingProfileDeleteRequestDTO;
import Team76.InternetSoftwareArchitecture.iservice.IUserDeleteAccountService;
import Team76.InternetSoftwareArchitecture.model.User;
import Team76.InternetSoftwareArchitecture.model.UserDeleteAccount;
import Team76.InternetSoftwareArchitecture.model.UserDeleteAccountStatus;
import Team76.InternetSoftwareArchitecture.repository.IUserDeleteAccountRepository;
import Team76.InternetSoftwareArchitecture.repository.IUserRepository;

@Service
public class UserDeleteAccountService implements IUserDeleteAccountService {

	private IUserDeleteAccountRepository userDeleteAccountRepository;

	private IUserRepository userRepository;

	private EmailService emailService;

	@Autowired
	public UserDeleteAccountService(IUserDeleteAccountRepository userDeleteAccountRepository,
			IUserRepository userRepository, EmailService emailService) {
		this.userDeleteAccountRepository = userDeleteAccountRepository;
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Override
	public UserDeleteAccount sendDeleteClientAccountRequest(UserDeleteAccountRequestDTO userDeleteAccountDTO) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDeleteAccount userDeleteAccount = new UserDeleteAccount();
		userDeleteAccount.setReason(userDeleteAccountDTO.getReason());
		userDeleteAccount.setUser(userRepository.findByUserId(user.getUserId()));
		userDeleteAccount.setUserDeleteAccountStatus(UserDeleteAccountStatus.WAITING);
		return userDeleteAccountRepository.save(userDeleteAccount);
	}

	@Override
	public List<WaitingProfileDeleteRequestDTO> findProfileDeleteRequests() {
		List<UserDeleteAccount> userDeleteAccounts = userDeleteAccountRepository.findWaitingProfileDeleteRequests();
		List<WaitingProfileDeleteRequestDTO> requestDTOs = new ArrayList<WaitingProfileDeleteRequestDTO>();
		for (UserDeleteAccount userDeleteAccount : userDeleteAccounts) {
			User u = userRepository.findByUserId(userDeleteAccount.getUser().getUserId());
			requestDTOs.add(new WaitingProfileDeleteRequestDTO(userDeleteAccount.getUserDeleteAccountId(), u.getFirstName(), u.getLastName(), u.getEmail(),
					userDeleteAccount.getReason()));
		}

		return requestDTOs;
	}

	@Override
	public Boolean accpetRequest(AnswerProfileDeleteRequestDTO answerProfileDeleteRequestDTO) {
		User u = userRepository.findByEmail(answerProfileDeleteRequestDTO.getEmail());
		u.setEnabled(false);
		userRepository.save(u);
		UserDeleteAccount userDeleteAccount = userDeleteAccountRepository.findUserDeleteAccountByUserDeleteAccountId(answerProfileDeleteRequestDTO.getProfileDeleteRequestId());
		userDeleteAccount.setUserDeleteAccountStatus(UserDeleteAccountStatus.ACCEPTED);
		userDeleteAccountRepository.save(userDeleteAccount);
		try {
			sendAcceptedProfileDeleteEmail(answerProfileDeleteRequestDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private void sendAcceptedProfileDeleteEmail(AnswerProfileDeleteRequestDTO answerProfileDeleteRequestDTO) {
		StringBuilder text = new StringBuilder();
		text.append("We have accepted your profile delete request.");
		text.append("\nComment:\n");
		text.append(answerProfileDeleteRequestDTO.getComment());
		emailService.sendNotificaitionAsync(answerProfileDeleteRequestDTO.getEmail(), "Accepted profile delete request",
				text.toString());

	}

	public Boolean declineRequest(AnswerProfileDeleteRequestDTO answerProfileDeleteRequestDTO) {
		UserDeleteAccount userDeleteAccount = userDeleteAccountRepository.findUserDeleteAccountByUserDeleteAccountId(answerProfileDeleteRequestDTO.getProfileDeleteRequestId());
		userDeleteAccount.setUserDeleteAccountStatus(UserDeleteAccountStatus.DECLINED);
		userDeleteAccountRepository.save(userDeleteAccount);
		try {
			sendDeclinedProfileDeleteEmail(answerProfileDeleteRequestDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private void sendDeclinedProfileDeleteEmail(AnswerProfileDeleteRequestDTO answerProfileDeleteRequestDTO) {
		StringBuilder text = new StringBuilder();
		text.append("We have declined your profile delete request.");
		text.append("\nComment:\n");
		text.append(answerProfileDeleteRequestDTO.getComment());
		emailService.sendNotificaitionAsync(answerProfileDeleteRequestDTO.getEmail(), "Declined profile delete request",
				text.toString());
	}

}
