package Team76.InternetSoftwareArchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Team76.InternetSoftwareArchitecture.dto.AnswerComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ComplaintRequestDTO;
import Team76.InternetSoftwareArchitecture.dto.ObjectComplaintDTO;
import Team76.InternetSoftwareArchitecture.dto.UserComplaintDTO;
import Team76.InternetSoftwareArchitecture.iservice.IComplaintService;
import Team76.InternetSoftwareArchitecture.model.Client;
import Team76.InternetSoftwareArchitecture.model.Complaint;
import Team76.InternetSoftwareArchitecture.model.ComplaintStatus;
import Team76.InternetSoftwareArchitecture.model.SystemAdministrator;
import Team76.InternetSoftwareArchitecture.repository.IComplaintRepository;
import Team76.InternetSoftwareArchitecture.repository.ICottageRepository;
import Team76.InternetSoftwareArchitecture.repository.IShipRepository;
import Team76.InternetSoftwareArchitecture.repository.IUserRepository;

@Service
public class ComplaintService implements IComplaintService {
	
	private IComplaintRepository complaintRepository;
	private ICottageRepository cottageRepository;
	private IShipRepository shipRepository;
	private IUserRepository userRepository;
	private EmailService emailService;
	
	@Autowired
	public ComplaintService(IComplaintRepository complaintRepository, ICottageRepository cottageRepository, IShipRepository shipRepository,
			IUserRepository userRepository, EmailService emailService) {
		this.complaintRepository = complaintRepository;
		this.shipRepository = shipRepository;
		this.cottageRepository = cottageRepository;
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Override
	public Complaint writeCottageComplaint(ComplaintRequestDTO complaintRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint complaint = new Complaint();
		complaint.setClient((Client)userRepository.findByEmail(client.getEmail()));
		complaint.setComment(complaintRequestDTO.getComment());
		complaint.setCottage(cottageRepository.findByCottageId(complaintRequestDTO.getComplaintEntityId()));
		complaint.setComplaintStatus(ComplaintStatus.WAITING);
		return complaintRepository.save(complaint);
	}
	
	@Override
	public Complaint writeShipComplaint(ComplaintRequestDTO complaintRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint complaint = new Complaint();
		complaint.setClient((Client)userRepository.findByEmail(client.getEmail()));
		complaint.setComment(complaintRequestDTO.getComment());
		complaint.setShip(shipRepository.findByShipId(complaintRequestDTO.getComplaintEntityId()));
		complaint.setComplaintStatus(ComplaintStatus.WAITING);
		return complaintRepository.save(complaint);
	}
	
	
	@Override
	public Complaint writeUserComplaint(ComplaintRequestDTO complaintRequestDTO) {
		Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint complaint = new Complaint();
		complaint.setClient((Client)userRepository.findByEmail(client.getEmail()));
		complaint.setComment(complaintRequestDTO.getComment());
		complaint.setUser(userRepository.findByUserId(complaintRequestDTO.getComplaintEntityId()));
		complaint.setComplaintStatus(ComplaintStatus.WAITING);
		return complaintRepository.save(complaint);
	}
	
	@Override
	public List<ObjectComplaintDTO> getCottageComplaints() {
		List<Complaint> cottageComplaints = new ArrayList<Complaint>();
		cottageComplaints = complaintRepository.getCottageComplaints();
		return cottageComplaints.stream().map(c -> new ObjectComplaintDTO(c.getComplaintId(), c.getCottage().getName(), c.getComment(), c.getClient().getFirstName(), c.getClient().getLastName(), c.getClient().getEmail(), c.getCottage().getCottageOwner().getFirstName(), c.getCottage().getCottageOwner().getLastName(), c.getCottage().getCottageOwner().getEmail())).
				collect(Collectors.toList());
	}

	@Override
	public List<ObjectComplaintDTO> getShipComplaints() {
		List<Complaint> shipComplaints = new ArrayList<Complaint>();
		shipComplaints = complaintRepository.getShipComplaints();
		return shipComplaints.stream().map(s -> new ObjectComplaintDTO(s.getComplaintId(), s.getShip().getName(), s.getComment(), s.getClient().getFirstName(), s.getClient().getLastName(), s.getClient().getEmail(), s.getShip().getShipOwner().getFirstName(), s.getShip().getShipOwner().getLastName(), s.getShip().getShipOwner().getEmail())).
				collect(Collectors.toList());
	}

	@Override
	public List<UserComplaintDTO> getUserComplaints() {
		List<Complaint> userComplaints = new ArrayList<Complaint>();
		userComplaints = complaintRepository.getUserComplaints();
		return userComplaints.stream().map(u -> new UserComplaintDTO(u.getComplaintId(), u.getUser().getFirstName(), u.getUser().getLastName(),  u.getUser().getEmail(),  u.getComment(), u.getClient().getFirstName(), u.getClient().getLastName(), u.getClient().getEmail())).collect(Collectors.toList());
	}
	
	@Override
	public Boolean accpetCottageComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.APPROVED);
		complaintRepository.save(c);
		try {
			sendAcceptedCottageComplaintEmails(answerComplaintRequestDTO, c.getComment(), getSystemAdministratorName(systemAdministrator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean declineCottageComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.DECLINED);
		complaintRepository.save(c);
		try {
			sendDeclinedCottageComplaintEmails(answerComplaintRequestDTO, c.getComment(), getSystemAdministratorName(systemAdministrator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean acceptShipComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.APPROVED);
		complaintRepository.save(c);
		try {
			sendAcceptedShipComplaintEmails(answerComplaintRequestDTO, c.getComment(), getSystemAdministratorName(systemAdministrator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean declineShipComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.DECLINED);
		complaintRepository.save(c);
		try {
			sendDeclinedShipComplaintEmails(answerComplaintRequestDTO, c.getComment(), getSystemAdministratorName(systemAdministrator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean acceptUserComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.APPROVED);
		complaintRepository.save(c);
		try {
			sendAcceptedUserComplaintEmails(answerComplaintRequestDTO, c.getComment(), getSystemAdministratorName(systemAdministrator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean declineUserComplaintRequest(AnswerComplaintRequestDTO answerComplaintRequestDTO) {
		SystemAdministrator systemAdministrator = (SystemAdministrator) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Complaint c = complaintRepository.findById(answerComplaintRequestDTO.getComplaintRequestId()).get();
		c.setComplaintStatus(ComplaintStatus.DECLINED);
		complaintRepository.save(c);
		try {
			sendDeclinedUserComplaintEmails(answerComplaintRequestDTO, c.getComment(), getSystemAdministratorName(systemAdministrator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String getSystemAdministratorName(SystemAdministrator systemAdministrator) {
		StringBuilder systemAdministratorName = new StringBuilder();
		systemAdministratorName.append(systemAdministrator.getFirstName());
		systemAdministratorName.append(" ");
		systemAdministratorName.append(systemAdministrator.getLastName());
		return systemAdministratorName.toString();
	}
	
	private void sendAcceptedCottageComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		String captionFirstMessageText = "We have accepted your cottage complaint request.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Accepted cottage complaint request", createFirstMessageText(answerComplaintRequestDTO, captionFirstMessageText, complaintComment, systemAdministratorName));
		
		String captionSecondMessageText = "We have accepted the complaint against your cottage.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Cottage complaint", createSecondMessageText(answerComplaintRequestDTO, captionSecondMessageText, complaintComment, systemAdministratorName));
	}
	
	private void sendAcceptedShipComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		String captionFirstMessageText = "We have accepted your ship complaint request.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Accepted ship complaint request", createFirstMessageText(answerComplaintRequestDTO, captionFirstMessageText, complaintComment, systemAdministratorName));
		
		String captionSecondMessageText = "We have accepted the complaint against your ship.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Ship complaint", createSecondMessageText(answerComplaintRequestDTO, captionSecondMessageText, complaintComment, systemAdministratorName));
	}
	
	private void sendAcceptedUserComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		String captionFirstMessageText = "We have accepted your complaint request.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Accepted complaint request", createFirstMessageText(answerComplaintRequestDTO, captionFirstMessageText, complaintComment, systemAdministratorName));
		
		String captionSecondMessageText = "We have accepted the complaint against you.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Complaint", createSecondMessageText(answerComplaintRequestDTO, captionSecondMessageText, complaintComment, systemAdministratorName));
		
	}
	
	private void sendDeclinedCottageComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		String captionFirstMessageText = "We have declined your cottage complaint request.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Declined cottage complaint request", createFirstMessageText(answerComplaintRequestDTO, captionFirstMessageText, complaintComment, systemAdministratorName));
		
		String captionSecondMessageText = "We have declined the complaint against your cottage.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Cottage complaint", createSecondMessageText(answerComplaintRequestDTO, captionSecondMessageText, complaintComment, systemAdministratorName));
	}
	
	private void sendDeclinedShipComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		String captionFirstMessageText = "We have declined your ship complaint request.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Declined ship complaint request", createFirstMessageText(answerComplaintRequestDTO, captionFirstMessageText, complaintComment, systemAdministratorName));
		
		String captionSecondMessageText = "We have declined the complaint against your ship.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Ship complaint", createSecondMessageText(answerComplaintRequestDTO, captionSecondMessageText, complaintComment, systemAdministratorName));
	}
	
	private void sendDeclinedUserComplaintEmails(AnswerComplaintRequestDTO answerComplaintRequestDTO, String complaintComment, String systemAdministratorName) {
		String captionFirstMessageText = "We have declined your complaint request.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getClientEmail(), "Declined complaint request", createFirstMessageText(answerComplaintRequestDTO, captionFirstMessageText, complaintComment, systemAdministratorName));
		
		String captionSecondMessageText = "We have declined the complaint against you.";
		emailService.sendNotificaitionAsync(answerComplaintRequestDTO.getUserEmail(), "Complaint", createSecondMessageText(answerComplaintRequestDTO, captionSecondMessageText, complaintComment, systemAdministratorName));
	}
	
	private String createFirstMessageText(AnswerComplaintRequestDTO answerComplaintRequestDTO, String captionFirstMessageText, String complaintComment, String systemAdministratorName) {
		StringBuilder firstMessageText = new StringBuilder();
		firstMessageText.append(captionFirstMessageText);
		firstMessageText.append("\nYour complaint: \"");
		firstMessageText.append(complaintComment);
		firstMessageText.append("\"");
		firstMessageText.append("\nAnswer: ");
		firstMessageText.append(answerComplaintRequestDTO.getComment());
		firstMessageText.append("\n");
		firstMessageText.append(systemAdministratorName);
		return firstMessageText.toString();
	}
	
	private String createSecondMessageText(AnswerComplaintRequestDTO answerComplaintRequestDTO, String captionSecondMessageText, String complaintComment, String systemAdministratorName) {
		StringBuilder secondMessageText = new StringBuilder();
		secondMessageText.append(captionSecondMessageText);
		secondMessageText.append("\nComplaint: \"");
		secondMessageText.append(complaintComment);
		secondMessageText.append("\"");
		secondMessageText.append("\nAnswer: ");
		secondMessageText.append(answerComplaintRequestDTO.getComment());
		secondMessageText.append("\n");
		secondMessageText.append(systemAdministratorName);
		return secondMessageText.toString();
	}

}
